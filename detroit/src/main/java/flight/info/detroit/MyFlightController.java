package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import flight.info.detroit.flightdao.FlightTripDao;

@Controller
public class MyFlightController {

	@Autowired
	FlightTripDao flightTripDao;

	@Autowired
	MapsInfoApiService mapsApiService;

	@Autowired
	private FlightStatsApiServices flightStatsApiServices;

	// INDEX take user input for flight number and send to API
	@RequestMapping("/")
	public ModelAndView showFlightSearch() {

		return new ModelAndView("flightsearch");
	}

	@RequestMapping("/flightresults")
	public ModelAndView showFlightResults(@RequestParam("flightcode") String flightCode,
			@RequestParam("origin") String origin, @RequestParam(name = "bags", required = false) Boolean hasBags) {

		if (!(flightCode.matches("^[A-Za-z0-9]{2}\\d{1,4}$"))) {
			ModelAndView mav = new ModelAndView("flightsearch");
			mav.addObject("message", "Invalid flight number or flight code. Please re-enter.");
			return mav;
		}

		// split the flight search into airline and flight number for API url and accept
		// flight numbers ranging from 3-6 characters in length
		String airline = flightCode.substring(0, 2);
		String flightNumber = flightCode.substring(2);

		FlightStatus flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);

		if (flightstatus == null) {
			ModelAndView mav = new ModelAndView("flightsearch");
			mav.addObject("message", "The flight information you entered could not be found. Please try again.");
			return mav;
		}
		String arrivalLocation = flightstatus.getAirportResources().getArrivalTerminal();
		flightTripDao.create(flightstatus);

		Long dur = mapsApiService.getTravelWithTraffic(origin, arrivalLocation);

		// send duration in seconds to the database
		flightstatus.setDriveOrigin(origin);
		flightstatus.setDuration(dur);
		flightTripDao.updateFlight(flightstatus);

		LocalDateTime driverDeptTime;

		if (hasBags != null) {
			// storing the calculated departure time for driver / user with checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureWithBags(flightstatus, dur);
			flightstatus.setHasBags(true);
			flightTripDao.updateFlight(flightstatus);

		} else {

			// storing the calculated departure time for driver / user with no checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureNoBags(flightstatus, dur);
			flightstatus.setHasBags(false);
			flightTripDao.updateFlight(flightstatus);
		}

		// JUMBO JET CHECK check if aircraft will add additional time or smaller jet
		// decreases time
		Long planeSizeAdjustment = FlightMathCalculator.checkPlaneSize(flightstatus);
		// ARRIVAL GATE CHECK checks to see how far the walk from the gate to the curb
		// is
		Long walkingTimeAdjustment = FlightMathCalculator.checkGateWalkTime(flightstatus);

		driverDeptTime = driverDeptTime.plusMinutes(planeSizeAdjustment);
		driverDeptTime = driverDeptTime.plusMinutes(walkingTimeAdjustment);

		// sending updated driver departure time (including walk time and plane size
		// adjustment)
		flightstatus.setDriverDeparture(driverDeptTime);
		flightTripDao.updateFlight(flightstatus);
		// storing the calcualted driver departure time in a string, reformatted forhumans
		String formattedDriverDeptTime = driverDeptTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

		// sending reformatted driver departure time to database
		flightstatus.setFmtDriverDepartureTime(formattedDriverDeptTime);
		flightTripDao.updateFlight(flightstatus);
		// sending airline passenger gate assignment to database

		// getting formatted time at door from Flight Math Calc

		String timeAtDoor = FlightMathCalculator.getPickupTime(dur, driverDeptTime);
		String gateArrival = FlightMathCalculator.getFormattedGateArrival(flightstatus);

		// store formatted time at door and gate arrival in DB
		flightstatus.setFmtGateArrival(gateArrival);
		flightstatus.setFmtPickupTime(timeAtDoor);
		flightTripDao.updateFlight(flightstatus);
		ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);

		// times being compared for timelinepoint
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		// get non trucated localdatetime metrics from API for comparison

		/// use estimated gate arrival (more accurate) if its available, if not use
		/// scheduled (less accurate)
		String estGateArrivalS = "";

		try {
			estGateArrivalS = flightstatus.getOperationalTimes().getEstimatedGateArrival().getDateLocal();

		} catch (NullPointerException e) {
			estGateArrivalS = flightstatus.getOperationalTimes().getScheduledGateArrival().getDateLocal();

		}

		LocalDateTime gateArrivalTimeline = LocalDateTime.parse(estGateArrivalS, formatter);
		LocalDateTime timeAtDoorTimeline = FlightMathCalculator.getPickupTimeLdt(dur, driverDeptTime);

		// compare to current time to see if this phase of the pickup is complete
		boolean gateArrivalBool = FlightMathCalculator.PickupStageComplete(gateArrival);
		boolean driverDepartureBool = FlightMathCalculator.PickupStageComplete(formattedDriverDeptTime);
		boolean timeAtDoorBool = FlightMathCalculator.PickupStageComplete(timeAtDoor);

		ArrayList<TimelinePoint> timeLineList = new ArrayList<TimelinePoint>();
		TimelinePoint driverDepartureTime = new TimelinePoint("Driver Departure Time", driverDeptTime,
				driverDepartureBool);
		timeLineList.add(driverDepartureTime);
		TimelinePoint airplaneGateArrival = new TimelinePoint("Airplane Arrival", gateArrivalTimeline, gateArrivalBool);
		timeLineList.add(airplaneGateArrival);
		TimelinePoint passengerDoorPickup = new TimelinePoint("Passenger Ready At Door", timeAtDoorTimeline,
				timeAtDoorBool);
		timeLineList.add(passengerDoorPickup);
		Collections.sort(timeLineList);

		// move progress bar along based on whether gate arrival time, time at door, and
		// driver departure times have already occurred.

		List<Boolean> progressBarBooleans = new ArrayList<Boolean>();
		progressBarBooleans.add(gateArrivalBool);
		progressBarBooleans.add(driverDepartureBool);
		progressBarBooleans.add(timeAtDoorBool);

		int progressBarCounter = 0;
		for (int i = 0; i < progressBarBooleans.size(); i++) {
			if (progressBarBooleans.get(i)) {
				progressBarCounter++;
			}
		}

		// Explicit cast as a double to avoid int math of 2/3 = 0. \
		// Subtracts 1 from numerator and 1 from denominator so that 2/3 becomes 1/2 =
		// 50% progress
		double progressBarMvt = 100 * ((double) (progressBarCounter - 1) / ((double) (progressBarBooleans.size() - 1)));
		// E.g. for three progress points, we want 0 = 0%, 1= 0%, 2=50%, 3=100%.
		// The expression above does not work for 0 = 0%. Fix is to look for negative
		// numbers, and assign them a value of zero.
		if (progressBarMvt < 0) {
			progressBarMvt = 0;
		}

		// send bags value to JSP
		Boolean bags = flightstatus.getHasBags();

		// show the human readable string of duration in traffic with minutes and
		// seconds to the user
		String showDriveTimeInTrafficMinsSecs = FlightMathCalculator.humanReadableDuration(dur);

		mav.addObject("walktime", walkingTimeAdjustment);
		mav.addObject("planesize", planeSizeAdjustment);
		mav.addObject("bags", bags);
		mav.addObject("traffic", showDriveTimeInTrafficMinsSecs);
		mav.addObject("origlocation", origin);
		// placing reformatted times on jsp after reformatting to 12hr
		mav.addObject("grounddepttime", formattedDriverDeptTime);
		mav.addObject("timeatdoor", timeAtDoor);
		mav.addObject("gatearrival", gateArrival);
		mav.addObject("timelinePoint", timeLineList);
		mav.addObject("progresspercent", progressBarMvt);

		return mav;

	}

// SINGLE FLIGHT DETAIL ACCESSED FROM DB + API
	@RequestMapping("/flights/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		
		// grab old flight status from DB to get flight number 
		FlightStatus flightStatus = flightTripDao.findById(id);	
		// call Flight Stats API again to get updatd flight status info
		FlightStatus flightStatusUpdated = flightStatsApiServices.searchFlight(flightTripDao.findById(id).getCarrierFsCode(), flightTripDao.findById(id).getFlightNumber().toString());
		String arrivalTerminal = flightStatusUpdated.getAirportResources().getArrivalTerminal(); 	
		// call the Google API again to get updated driving time 
		String origin = flightTripDao.findById(id).getDriverOrigin();
		Long durUpdated = mapsApiService.getTravelWithTraffic(origin, arrivalTerminal);
					
		LocalDateTime driverDeptTime;

		if (flightStatusUpdated.getHasBags()) {
			// storing the calculated departure time for driver / user with checked bags			
			driverDeptTime = FlightMathCalculator.driverDepartureWithBags(flightStatusUpdated, durUpdated);
		} else {
			// storing the calculated departure time for driver / user with no checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureNoBags(flightStatusUpdated, durUpdated);	
		}
		
		String estGateArrivalS = "";

		try {
			estGateArrivalS = flightStatusUpdated.getOperationalTimes().getEstimatedGateArrival().getDateLocal();
		} catch (NullPointerException e) {
			estGateArrivalS = flightStatusUpdated.getOperationalTimes().getScheduledGateArrival().getDateLocal();

		}
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		
		LocalDateTime gateArrivalTimeline = LocalDateTime.parse(estGateArrivalS, formatter);
		LocalDateTime timeAtDoorTimeline = FlightMathCalculator.getPickupTimeLdt(durUpdated, driverDeptTime);		
		// getting formatted time at door from Flight Math Calc
		String timeAtDoor = FlightMathCalculator.getPickupTime(durUpdated, driverDeptTime);
		String gateArrival = FlightMathCalculator.getFormattedGateArrival(flightStatusUpdated);
		String formattedDriverDeptTime = driverDeptTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));	
		// compare to current time to see if this phase of the pickup is complete
		boolean gateArrivalBool = FlightMathCalculator.PickupStageComplete(gateArrival);
		boolean driverDepartureBool = FlightMathCalculator.PickupStageComplete(formattedDriverDeptTime);
		boolean timeAtDoorBool = FlightMathCalculator.PickupStageComplete(timeAtDoor);		
		

		ArrayList<TimelinePoint> timeLineList = new ArrayList<TimelinePoint>();
		TimelinePoint driverDepartureTime = new TimelinePoint("Driver Departure Time", driverDeptTime, driverDepartureBool);
		timeLineList.add(driverDepartureTime);
		TimelinePoint airplaneGateArrival = new TimelinePoint("Airplane Arrival", gateArrivalTimeline, gateArrivalBool);
		timeLineList.add(airplaneGateArrival);
		TimelinePoint passengerDoorPickup = new TimelinePoint("Passenger Ready At Door", timeAtDoorTimeline, timeAtDoorBool);
		timeLineList.add(passengerDoorPickup);
		Collections.sort(timeLineList);		
		// move progress bar along based on whether gate arrival time, time at door, and
		// driver departure times have already occurred.
		List<Boolean> progressBarBooleans = new ArrayList<Boolean>();
		progressBarBooleans.add(gateArrivalBool);
		progressBarBooleans.add(driverDepartureBool);
		progressBarBooleans.add(timeAtDoorBool);

		int progressBarCounter = 0;
		for (int i = 0; i < progressBarBooleans.size(); i++) {
			if (progressBarBooleans.get(i)) {
				progressBarCounter++;
			}
		}

		// Explicit cast as a double to avoid int math of 2/3 = 0. \
		// Subtracts 1 from numerator and 1 from denominator so that 2/3 becomes 1/2 =
		// 50% progress
		double progressBarMvt = 100 * ((double) (progressBarCounter - 1) / ((double) (progressBarBooleans.size() - 1)));
		// E.g. for three progress points, we want 0 = 0%, 1= 0%, 2=50%, 3=100%.
		// The expression above does not work for 0 = 0%. Fix is to look for negative
		// numbers, and assign them a value of zero.
		if (progressBarMvt < 0) {
			progressBarMvt = 0;
		}

		ModelAndView mav = new ModelAndView("flightdetails", "flight", flightStatusUpdated);
		
		// send bags value to JSP
		Boolean bags = flightStatus.getHasBags();
		
		// show the human readable string of duration in traffic with minutes and seconds to the user
		String showDriveTimeInTrafficMinsSecs = FlightMathCalculator.humanReadableDuration(durUpdated);
		
		// JUMBO JET CHECK check if aircraft will add additional time or smaller jet
				// decreases time
				Long planeSizeAdjustment = FlightMathCalculator.checkPlaneSize(flightStatusUpdated);
				// ARRIVAL GATE CHECK checks to see how far the walk from the gate to the curb
				// is
				Long walkingTimeAdjustment = FlightMathCalculator.checkGateWalkTime(flightStatusUpdated);

				driverDeptTime = driverDeptTime.plusMinutes(planeSizeAdjustment);
				driverDeptTime = driverDeptTime.plusMinutes(walkingTimeAdjustment);

		mav.addObject("walktime", walkingTimeAdjustment);
		mav.addObject("planesize", planeSizeAdjustment);
		mav.addObject("bags", bags);
		mav.addObject("traffic", showDriveTimeInTrafficMinsSecs);
		mav.addObject("origlocation", origin);
		mav.addObject("grounddepttime", formattedDriverDeptTime);
		mav.addObject("timeatdoor", timeAtDoor);
		mav.addObject("gatearrival", gateArrival);
		mav.addObject("timelinePoint", timeLineList);
		mav.addObject("progresspercent", progressBarMvt);

		return mav;
	}

// LIST OF MULTIPLE FLIGHT RESULTS ACCESSED FROM DB
	@RequestMapping("/flightlist")
	public ModelAndView showList() {

		List<FlightStatus> listOfSearches = flightTripDao.findAll();
		ModelAndView mav = new ModelAndView("flightlist", "flights", listOfSearches);
		return mav;
	}

// DELETE AN ITEM / PRODUCT
	@RequestMapping("/flightstatus/delete")
	public ModelAndView delete(@RequestParam("id") Long id) {
		flightTripDao.deleteFlight(id);
		return new ModelAndView("redirect:/flightlist");
	}

// UPDATE DEPARTURE / PICKUP TIMING  
	@RequestMapping("/flightstatus/update")
	public ModelAndView update(@RequestParam("id") Long id) {
		// grab the necessary search strings from database to replicate user's search
		// and hold other use submitted data
		String airline = flightTripDao.findById(id).getCarrierFsCode();
		String flightNumber = flightTripDao.findById(id).getFlightNumber().toString();
		Boolean hasBags = flightTripDao.findById(id).getHasBags();
		String gateArrivalTime = flightTripDao.findById(id).getFmtGateArrival();
		String pickupTime = flightTripDao.findById(id).getFmtPickupTime();

		// call both APIS again to update data points for a flight
		FlightStatus updatedFs = flightStatsApiServices.searchFlight(airline, flightNumber);
		updatedFs.setId(id);
		updatedFs.setHasBags(hasBags);
		updatedFs.setFmtGateArrival(gateArrivalTime);
		updatedFs.setFmtPickupTime(pickupTime);

		String arrivalLocation = updatedFs.getAirportResources().getArrivalTerminal();
		Long updatedDur = mapsApiService.getTravelWithTraffic(flightTripDao.findById(id).getDriverOrigin(),
				arrivalLocation);
		updatedFs.setDriveDurationSec(updatedDur);

		// conditional logic to account for pickups with (if) and without bags (else)
		if (updatedFs.getHasBags()) {
			// calculate updated driver departure time with new traffic info
			LocalDateTime driverDeptTime = FlightMathCalculator.driverDepartureWithBags(updatedFs, updatedDur);
			String formattedDriverDeptTime = driverDeptTime.toLocalTime()
					.format(DateTimeFormatter.ofPattern("hh:mm a"));
			updatedFs.setDriverDeparture(driverDeptTime);
			updatedFs.setFmtDriverDepartureTime(formattedDriverDeptTime);

		} else {
			LocalDateTime driverDeptTime = FlightMathCalculator.driverDepartureNoBags(updatedFs, updatedDur);
			String formattedDriverDeptTime = driverDeptTime.toLocalTime()
					.format(DateTimeFormatter.ofPattern("hh:mm a"));
			updatedFs.setDriverDeparture(driverDeptTime);
			updatedFs.setFmtDriverDepartureTime(formattedDriverDeptTime);
		}

		updatedFs.setDriveOrigin(flightTripDao.findById(id).getDriverOrigin());

		flightTripDao.updateFlight(updatedFs);

		ModelAndView mav = new ModelAndView("redirect:/flightlist");

		return mav;
	}
}
