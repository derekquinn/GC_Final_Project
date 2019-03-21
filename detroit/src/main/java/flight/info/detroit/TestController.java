package flight.info.detroit;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
	@Autowired
	private FlightStatsApiServices flightStatsApiServices;

	@RequestMapping("findflight")
	public ModelAndView testingStuff() {
		
		ArrayList<FlightTracks> ft = flightStatsApiServices.searchFlightCode();
		ModelAndView mav = new ModelAndView("seachcode", "listofflights",ft );
		
		
		mav.addObject("airportInfo", flightStatsApiServices.searchAirportCode());
		
		return mav;
	}

	@RequestMapping("flightcode")
	public ModelAndView sendFlightInfo(@RequestParam("carr") String carrier, @RequestParam("num") String num) {
		ModelAndView mav = new ModelAndView("flightsearch", "flightNum", carrier + num);
		return mav;
	}
}
