package flight.info.detroit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import flight.info.detroit.model.OriginAndDestination;


@Controller
public class MyFlightController {
	
	@Autowired
	MapsInfoApiService mapsApiService;


//	@Autowired
//	private FlightStatsApiServices flightStatsApiServices;

	@RequestMapping("/")
	public ModelAndView showIndex() {
		// List<FlightStatus> flightstatus = flightStatsApiServices.getFlightStatus();
		return new ModelAndView("index");
	}

	
	@RequestMapping("/results")
	public ModelAndView deployResults() {
		mapsApiService.printDistanceMatrix();
		return new ModelAndView("results");
	}

}
