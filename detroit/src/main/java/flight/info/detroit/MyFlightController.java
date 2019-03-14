package flight.info.detroit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.model.Duration;

@Controller
public class MyFlightController {

	@Autowired
	MapsInfoApiService mapsApiService;

	@Autowired
	private FlightStatsApiServices flightStatsApiServices;



	// hard coded google distance matrix results test page with static destination /
	// origin
	@RequestMapping("/results")
	public ModelAndView deployResults() {

		Long dur = mapsApiService.getTravelWithTraffic("1 Park Ave, Detroit, MI");
		return new ModelAndView("results", "dur", dur);
	}

	// hard coded flight test results with a static flight number
	@RequestMapping("/flighttest")
	public ModelAndView showFlight() {
		List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();
		// System.out.println(flightstatus.toString());

		return new ModelAndView("flighttest", "flightstatus", flightstatus);
	}

	// take user input for flight number and send to API
	@RequestMapping("/")
	public ModelAndView showFlightSearch() {

		return new ModelAndView("index");
	}

	
	@RequestMapping("flightresults")
	public ModelAndView showFlightResults(
			@RequestParam("flightcode") String flightCode,
			@RequestParam("origin") String origin) {

		// split the flight search into airline and flight number for API url and accept
		// flight numbers ranging from 3-6 characters in length
		String airline = flightCode.substring(0, 2);
		String flightNumber = flightCode.substring(2);
		
		List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);
		// String test = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
		Long gateArrivalMetric = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
		Long dur = mapsApiService.getTravelWithTraffic(origin);
		//placing departure time on jsp
		
		LocalDateTime groundDeptTime = FlightMathCalculator.leaveOrginTime(flightstatus.get(0), dur);
		//Date Format
		SimpleDateFormat df = new SimpleDateFormat("hh:mm aa");
		
		
		//
		ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);
		
		mav.addObject("traffic", dur);
		mav.addObject("origlocation", origin);
		mav.addObject("gatearrivalmetric", gateArrivalMetric);
		mav.addObject("grounddepttime",groundDeptTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a")));
		
		return mav;	
	}
}
