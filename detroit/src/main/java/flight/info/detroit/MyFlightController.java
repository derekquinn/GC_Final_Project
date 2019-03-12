package flight.info.detroit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.model.DistanceMatrix;

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

	// hard coded google distance matrix results test page with static destination /
	// origin
	@RequestMapping("/results")
	public ModelAndView deployResults() {

		DistanceMatrix trix = mapsApiService.getTravelWithTraffic("1 Park Ave, Detroit, MI");
		return new ModelAndView("results", "", trix);
	}

	// hard coded flight test results with a static flight number
	@RequestMapping("/flighttest")
	public ModelAndView showFlight() {
		List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();
		// System.out.println(flightstatus.toString());

		return new ModelAndView("flighttest", "flightstatus", flightstatus);
	}

	// take user input for flight number and send to API
	@RequestMapping("/flightsearch")
	public ModelAndView showFlightSearch() {

		return new ModelAndView("flightsearch");
	}

	// split the flightsearch into airline and flight number for API url and accept
	// flight numbers ranging from 3-6 characters in length
	@RequestMapping("flightresults")
	public ModelAndView showFlightResults(@RequestParam("flightcode") String flightCode,
			@RequestParam("origin") String origin) {

		if (flightCode.length() == 6) {

			String airline = flightCode.substring(0, 2);
			String flightNumber = flightCode.substring(2, 6);
			List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);

			// String test = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));

			Long gateArrivalMetric = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));

			DistanceMatrix trix = mapsApiService.getTravelWithTraffic(origin);

			ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);
			mav.addObject("origin", trix);
			mav.addObject("origlocation", origin);
			mav.addObject("gatearrivalmetric", gateArrivalMetric);

			System.out.println(gateArrivalMetric);
			return mav;

		} else if (flightCode.length() == 5) {

			String airline = flightCode.substring(0, 2);
			String flightNumber = flightCode.substring(2, 5);
			List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);

			// String test = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
			Long gateArrivalMetric = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
			DistanceMatrix trix = mapsApiService.getTravelWithTraffic(origin);

			ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);
			mav.addObject("origin", trix);
			mav.addObject("origlocation", origin);
			mav.addObject("gatearrivalmetric", gateArrivalMetric);

			// System.out.println("Calculator Test: " + test);
			return mav;

		} else if (flightCode.length() == 4) {

			String airline = flightCode.substring(0, 2);
			String flightNumber = flightCode.substring(2, 4);
			List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);
			Long gateArrivalMetric = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
			// String test = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));

			DistanceMatrix trix = mapsApiService.getTravelWithTraffic(origin);

			ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);
			mav.addObject("origin", trix);
			mav.addObject("origlocation", origin);
			mav.addObject("gatearrivalmetric", gateArrivalMetric);
			// System.out.println("Calculator Test: " + test);
			return mav;

		} else if (flightCode.length() == 3) {

			String airline = flightCode.substring(0, 2);
			String flightNumber = flightCode.substring(2, 3);
			List<FlightStatus> flightstatus = flightStatsApiServices.searchFlight(airline, flightNumber);

			// String test = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
			Long gateArrivalMetric = FlightMathCalculator.gateArrivalMath(flightstatus.get(0));
			DistanceMatrix trix = mapsApiService.getTravelWithTraffic(origin);

			ModelAndView mav = new ModelAndView("flightresults", "flightstatus", flightstatus);
			mav.addObject("origin", trix);
			mav.addObject("origlocation", origin);
			mav.addObject("gatearrivalmetric", gateArrivalMetric);
			// System.out.println("Calculator Test: " + test);
			return mav;

		}
		return new ModelAndView("flightsearch");

	}
}
