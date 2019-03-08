package flight.info.detroit;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

public class FlightStatsApiServices {

	@Value("${flightstats.appid}")
	String appId;
	@Value("${flightstats.appkey}")
	String appKey;

	private RestTemplate restTemplateWithUserAgent;

	// This is an instance initialization block. It runs when a new instance of the
	// class is created--right before the constructor.
	{
		// This configures the restTemplateWithUserAgent to include a User-Agent header
		// with every HTTP request. Some of the APIs in this demo have a bug where
		// User-Agent is required.
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add(HttpHeaders.USER_AGENT, "Spring");
			return execution.execute(request, body);
		};
		restTemplateWithUserAgent = new RestTemplateBuilder().additionalInterceptors(interceptor).build();
	}

	public List<FlightStatus> getFlightStatus() {
		String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/flight/status/UX/0193/dep/2019/03/07?appId="
				+ appId + "&appKey=" + appKey + "&utc=false";
		FlightResponse response = restTemplateWithUserAgent.getForObject(url, FlightResponse.class);
		return response.getFlightStatuses();
	}

}
