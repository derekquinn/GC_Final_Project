package flight.info.detroit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyFlightController {

	@RequestMapping("/")
	public ModelAndView list() {

		return new ModelAndView("index");
	}

}
