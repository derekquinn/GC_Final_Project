package flight.info.detroit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyFlightController {

//	@Autowired
//	private FlightStatsApiServices flightStatsApiServices;

	@RequestMapping("/")
	public ModelAndView showIndex() {
		// List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();
		return new ModelAndView("index");
	}

}
