package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import flight.info.detroit.model.flightstats.DepartureDate;

@Controller
public class TestController {
	@Autowired
	private FlightStatsApiServices flightStatsApiServices;

	@RequestMapping("findflight")
	public ModelAndView testingStuff() {
		ModelAndView mav = new ModelAndView("seachcode", "listofflights", flightStatsApiServices.searchFlightCode());

		// flightStatsApiServices.searchFlightCode();
		//flightStatsApiServices.searchAirportCode();
		mav.addObject("airportInfo", flightStatsApiServices.searchAirportCode());
		return mav;
	}

	@RequestMapping("flightcode")
	public ModelAndView sendFlightInfo(@RequestParam("carr") String carrier, @RequestParam("num") String num) {
		ModelAndView mav = new ModelAndView("flightsearch", "flightNum", carrier + num);

		
		return mav;
	}
}
