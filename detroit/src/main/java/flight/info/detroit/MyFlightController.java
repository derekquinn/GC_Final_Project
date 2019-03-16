package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//
//	// hard coded google distance matrix results test page with static destination
//	@RequestMapping("/results")
//	public ModelAndView deployResults(FlightStatus fs) {
//		flightTripDao.create(fs);
//		ModelAndView mav = new ModelAndView("results");
//		// Long dur = mapsApiService.getTravelWithTraffic("1 Park Ave, Detroit, MI");
//		// mav.addObject("dur", dur);
//		mav.addObject("flightstatus", flightTripDao.findAll());
//		return mav;
//	}
//
//	// hard coded flight test results with a static flight number
//	@RequestMapping("/flighttest")
//	public ModelAndView showFlight() {
//		List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();
//		return new ModelAndView("flighttest", "flightstatus", flightstatus);
//	}

	// take user input for flight number and send to API
	@RequestMapping("/")
	public ModelAndView showFlightSearch() {

		return new ModelAndView("flightsearch");
	}

	@RequestMapping("/flightresults")
	public ModelAndView showFlightResults(@RequestParam("flightcode") String flightCode,
			@RequestParam("origin") String origin, @RequestParam(name = "bags", required = false) Boolean hasBags) {

		if (!(flightCode.matches("^[A-Za-z]{2}\\d{2,4}$"))) {
			ModelAndView mav = new ModelAndView("flightsearch");
			mav.addObject("message", "Invalid flight number or flight code. Please re-enter.");
			return mav;
		}

		// split the flight search into airline and flight number for API url and accept
		// flight numbers ranging from 3-6 characters in length
		String airline = flightCode.substring(0, 2);
		String flightNumber = flightCode.substring(2);

		List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);
		System.out.println(flightstatus.get(0));

		if (flightstatus.isEmpty()) {
			ModelAndView mav = new ModelAndView("flightsearch");
			mav.addObject("message", "The flight information you entered could not be found. Please try again.");
			return mav;
		}
		String arrivalLocation = flightstatus.get(0).getAirportResources().getArrivalTerminal();
		flightTripDao.create(flightstatus.get(0));

		Long dur = mapsApiService.getTravelWithTraffic(origin, arrivalLocation);

		// send duration in seconds to the database
		flightstatus.get(0).setDriveOrigin(origin);
		flightstatus.get(0).setDuration(dur);
		flightTripDao.updateFlight(flightstatus.get(0));

		LocalDateTime driverDeptTime;

		if (hasBags != null) {
			// storing the calculated departure time for driver / user with checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureWithBags(flightstatus.get(0), dur);
			flightstatus.get(0).setHasBags(true);
			flightTripDao.updateFlight(flightstatus.get(0));

		} else {

			// storing the calculated departure time for driver / user with no checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureNoBags(flightstatus.get(0), dur);
			flightstatus.get(0).setHasBags(false);
			flightTripDao.updateFlight(flightstatus.get(0));
		}

		// sending driver departure time to database
		flightstatus.get(0).setDriverDeparture(driverDeptTime);
		flightTripDao.updateFlight(flightstatus.get(0));
		// storing the calcualted driver departure time in a string, reformatted for
		// humans
		String formattedDriverDeptTime = driverDeptTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

		// sending reformatted driver departure time to database
		flightstatus.get(0).setFmtDriverDepartureTime(formattedDriverDeptTime);
		flightTripDao.updateFlight(flightstatus.get(0));
		// sending airline passenger gate assignment to database

		// getting formatted time at door from Flight Math Calc

		String timeAtDoor = FlightMathCalculator.getPickupTime(dur, driverDeptTime);
		String gateArrival = FlightMathCalculator.getFormattedGateArrival(flightstatus.get(0));

		// store formatted time at door and gate arrival in DB
		flightstatus.get(0).setFmtGateArrival(gateArrival);
		flightstatus.get(0).setFmtPickupTime(timeAtDoor);
		flightTripDao.updateFlight(flightstatus.get(0));
		ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);

		mav.addObject("traffic", dur);
		mav.addObject("origlocation", origin);
		// placing reformatted times on jsp after reformatting to 12hr
		mav.addObject("grounddepttime", formattedDriverDeptTime);
		mav.addObject("timeatdoor", timeAtDoor);
		mav.addObject("gatearrival", gateArrival);

		return mav;

	}

// SINGLE FLIGHT DETAIL ACCESSED FROM DB
	@RequestMapping("/flights/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		FlightStatus flightStatus = flightTripDao.findById(id);

		return new ModelAndView("flightdetails", "flight", flightStatus);
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
		List<FlightStatus> updatedFs = flightStatsApiServices.searchFlight(airline, flightNumber);
		updatedFs.get(0).setId(id);
		updatedFs.get(0).setHasBags(hasBags);
		updatedFs.get(0).setFmtGateArrival(gateArrivalTime);
		updatedFs.get(0).setFmtPickupTime(pickupTime);

		String arrivalLocation = updatedFs.get(0).getAirportResources().getArrivalTerminal();
		Long updatedDur = mapsApiService.getTravelWithTraffic(flightTripDao.findById(id).getDriverOrigin(),
				arrivalLocation);
		updatedFs.get(0).setDriveDurationSec(updatedDur);

		// conditional logic to account for pickups with (if) and without bags  (else) 
		if (updatedFs.get(0).getHasBags()) {
			// calculate updated driver departure time with new traffic info
			LocalDateTime driverDeptTime = FlightMathCalculator.driverDepartureWithBags(updatedFs.get(0), updatedDur);
			String formattedDriverDeptTime = driverDeptTime.toLocalTime()
					.format(DateTimeFormatter.ofPattern("hh:mm a"));
			updatedFs.get(0).setDriverDeparture(driverDeptTime);
			updatedFs.get(0).setFmtDriverDepartureTime(formattedDriverDeptTime);
			
			
		} else {
			LocalDateTime driverDeptTime = FlightMathCalculator.driverDepartureNoBags(updatedFs.get(0), updatedDur);
			String formattedDriverDeptTime = driverDeptTime.toLocalTime()
					.format(DateTimeFormatter.ofPattern("hh:mm a"));
			updatedFs.get(0).setDriverDeparture(driverDeptTime);
			updatedFs.get(0).setFmtDriverDepartureTime(formattedDriverDeptTime);
		}

		updatedFs.get(0).setDriveOrigin(flightTripDao.findById(id).getDriverOrigin());

		flightTripDao.updateFlight(updatedFs.get(0));

		ModelAndView mav = new ModelAndView("redirect:/flightlist");

		return mav;
	}
}	
