package flight.info.detroit;

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
		// flightStatsApiServices.searchFlightCode();

		return new ModelAndView("seachcode", "listofflights", flightStatsApiServices.searchFlightCode());
	}

	@RequestMapping("flightcode")
	public ModelAndView sendFlightInfo(@RequestParam("carr") String carrier, @RequestParam("num") String num) {
		// flightStatsApiServices.searchFlightCode();

		return new ModelAndView("flightsearch", "flightNum", carrier + num);
	}
}
