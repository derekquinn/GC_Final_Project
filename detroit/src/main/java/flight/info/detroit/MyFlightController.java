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

		if (flightstatus==null) {
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
		
		// JUMBO JET CHECK check if aircraft will add additional time or smaller jet decreases time 	
		Long planeSizeAdjustment = FlightMathCalculator.checkPlaneSize(flightstatus);
		System.out.println(planeSizeAdjustment);
		System.out.println("departure before plane size math: " + driverDeptTime);
		driverDeptTime = driverDeptTime.plusMinutes(planeSizeAdjustment);
		System.out.println("departure after plane size math: " + driverDeptTime);
		// sending driver departure time to database
		flightstatus.setDriverDeparture(driverDeptTime);
		flightTripDao.updateFlight(flightstatus);
		// storing the calcualted driver departure time in a string, reformatted for humans
		String formattedDriverDeptTime = driverDeptTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

		System.out.println("you got here bitch" + FlightMathCalculator.checkGateWalkTime(flightstatus));
		
		
		
		
		
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
		
		/// use estimated gate arrival (more accurate) if its available, if not use scheduled (less accurate)
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
		TimelinePoint driverDepartureTime = new TimelinePoint ("Driver Departure Time", driverDeptTime, driverDepartureBool);
		timeLineList.add(driverDepartureTime);
		TimelinePoint airplaneGateArrival = new TimelinePoint ("Airplane Arrival", gateArrivalTimeline, gateArrivalBool);
		timeLineList.add(airplaneGateArrival);
		TimelinePoint passengerDoorPickup = new TimelinePoint("Passenger Ready At Door", timeAtDoorTimeline, timeAtDoorBool);
		timeLineList.add(passengerDoorPickup);	
		Collections.sort(timeLineList);
		
		// send bags value to JSP
		Boolean bags = flightstatus.getHasBags();
		mav.addObject("bags", bags);
		mav.addObject("traffic", dur);
		mav.addObject("origlocation", origin);
		// placing reformatted times on jsp after reformatting to 12hr
		mav.addObject("grounddepttime", formattedDriverDeptTime);
		mav.addObject("timeatdoor", timeAtDoor);
		mav.addObject("gatearrival", gateArrival);
		mav.addObject("timelinePoint", timeLineList);

		
		
		return mav;

	}

// SINGLE FLIGHT DETAIL ACCESSED FROM DB
	@RequestMapping("/flights/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		FlightStatus flightStatus = flightTripDao.findById(id);
		
		ModelAndView mav = new ModelAndView("flightdetails", "flight", flightStatus);
		
		Long progressBar = FlightMathCalculator.getProgressBarMetric(flightStatus);
		mav.addObject("progressbar", progressBar);
		
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
		// grab the necessary search strings from database to replicate user's search and hold other use submitted data
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
 

		// conditional logic to account for pickups with (if) and without bags  (else) 
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
