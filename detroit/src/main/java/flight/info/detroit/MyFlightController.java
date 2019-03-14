package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	// hard coded google distance matrix results test page with static destination
	@RequestMapping("/results")
	public ModelAndView deployResults(FlightStatus fs) {
		flightTripDao.create(fs);
		ModelAndView mav = new ModelAndView("results");
		Long dur = mapsApiService.getTravelWithTraffic("1 Park Ave, Detroit, MI");
		mav.addObject("dur", dur);
		mav.addObject("flightstatus", flightTripDao.findAll());
		return mav;
	}

	// hard coded flight test results with a static flight number
	@RequestMapping("/flighttest")
	public ModelAndView showFlight() {
		List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();

		return new ModelAndView("flighttest", "flightstatus", flightstatus);
	}

	// take user input for flight number and send to API
	@RequestMapping("/")
	public ModelAndView showFlightSearch() {

		return new ModelAndView("flightsearch");
	}

	@RequestMapping("/flightresults")
	public ModelAndView showFlightResults(@RequestParam("flightcode") String flightCode,
			@RequestParam("origin") String origin, @RequestParam(name = "bags", required = false) Boolean checkedBags) {

		
		if (!(flightCode.matches("^[A-Za-z]{2}\\d{2,4}$")) ) { 
			  ModelAndView mav = new ModelAndView("flightsearch"); 
			  mav.addObject("message", "Invalid flight number or flight code. Please re-enter."); 
			  return mav;
		  }
		  
		 
		// split the flight search into airline and flight number for API url and accept
		// flight numbers ranging from 3-6 characters in length
		String airline = flightCode.substring(0, 2);
		String flightNumber = flightCode.substring(2);

		List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);
		if (flightstatus.isEmpty()) {
			ModelAndView mav = new ModelAndView("flightsearch");
			mav.addObject("message", "The flight information you entered could not be found. Please try again.");
			return mav;
		}
		
		flightTripDao.create(flightstatus.get(0));
		
		

		Long gateArrivalMetric = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
		Long dur = mapsApiService.getTravelWithTraffic(origin);

		// send duration in seconds to the database
		flightstatus.get(0).setDuration(dur);
		flightTripDao.update(flightstatus.get(0));

		LocalDateTime driverDeptTime;

		if (checkedBags != null) {
			// storing the calculated departure time for driver / user with checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureWithBags(flightstatus.get(0), dur);
		} else {

			// storing the calculated departure time for driver / user with no checked bags
			driverDeptTime = FlightMathCalculator.driverDepartureNoBags(flightstatus.get(0), dur);
		}

		// sending driver departure time to database
		flightstatus.get(0).setDriverDeparture(driverDeptTime);
		flightTripDao.update(flightstatus.get(0));
		// storing the calcualted driver departure time in a string, reformatted for
		// humans
		String formattedDriverDeptTime = driverDeptTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
		// sending reformatted driver departure time to database
		flightstatus.get(0).setFmtDriverDepartureTime(formattedDriverDeptTime);
		flightTripDao.update(flightstatus.get(0));
		// sending airline passenger gate assignment to database
		
	
		ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);

		mav.addObject("traffic", dur);
		mav.addObject("origlocation", origin);
		mav.addObject("gatearrivalmetric", gateArrivalMetric);
		// placing reformatted driver departure time on jsp after reformatting to 12hr
		// time
		mav.addObject("grounddepttime", formattedDriverDeptTime);
		
		return mav;
		
		
	}

	@RequestMapping("flightlist")
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

//Update and item
	@RequestMapping("/flightstatus/update")
	public ModelAndView update(FlightStatus fs) {
		flightTripDao.updateFlight(fs);
		return new ModelAndView("redirect:/flightlist");

	}

}