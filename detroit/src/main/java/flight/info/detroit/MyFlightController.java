package flight.info.detroit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyFlightController {

	@Autowired
	MapsInfoApiService mapsApiService;

	@Autowired
	private FlightStatsApiServices flightStatsApiServices;

	@RequestMapping("/")
	public ModelAndView showIndex() {

		return new ModelAndView("index");
	}

// hard coded google distance matrix results test page with static destination / origin 
	@RequestMapping("/results")
	public ModelAndView deployResults() {
		mapsApiService.printDistanceMatrix();
		return new ModelAndView("results");
	}

// hard coded flight test results with a static flight number 
	@RequestMapping("/flighttest")
	public ModelAndView showFlight() {
		List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();
		System.out.println(flightstatus.toString());
		return new ModelAndView("flighttest", "flightstatus", flightstatus);
	}

// take user input for flight number and send to API
	@RequestMapping("/flightsearch")
	public ModelAndView showFlightSearch() {
		
		
		return new ModelAndView("flightsearch");
	}

// split the flightsearch into airline and flight number for API url
	@RequestMapping("flightresults")
	public ModelAndView showFlightResults(@RequestParam("flightcode") String flightCode) {

// need to add conditional logic in case the flightCode is different length (IE AA67 vs AA067)
		String airline = flightCode.substring(0, 2);
		String flightNumber = flightCode.substring(2, 6);

		List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);
		
		System.out.println(flightstatus.toString());

		ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);

		return mav;
	}
}
