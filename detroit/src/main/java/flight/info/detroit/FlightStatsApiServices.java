package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
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

	// hard coded flight number for testing purposes

	public List<FlightStatus> getFlightStatus() {
		String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/flight/status/UX/0193/dep/2019/03/07?appId="
				+ appId + "&appKey=" + appKey + "&utc=false";
		FlightResponse response = restTemplateWithUserAgent.getForObject(url, FlightResponse.class);
		return response.getFlightStatuses();
	}

	// allows API to respond to user search for any flight

	public FlightStatus searchFlight(String airline, String flightNumber) {
		// puts todays date in the URL as string
		List<FlightStatus> flightStatus = new ArrayList<FlightStatus>();
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/flight/status/" + airline + "/"
				+ flightNumber + "/dep/" + today + "?appId=" + appId + "&appKey=" + appKey + "&utc=false";

		FlightResponse response = restTemplateWithUserAgent.getForObject(url, FlightResponse.class);

		// store the response temporarily
		flightStatus = response.getFlightStatuses();
		// loop through the response and find the flight landing at DTW
		for (int i = 0; i < flightStatus.size(); i++) {
			
			String arrivalCode = flightStatus.get(i).getArrivalAirportFsCode();

			if (arrivalCode.equals("DTW")) {
				return flightStatus.get(i);
			}
		}

		return flightStatus.get(0);
	}

	public ArrayList<FlightTracks> searchFlightCode() {
		// puts todays date in the URL as string
		String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));


		String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/tracks/DTW/arr" + "?appId="
				+ appId + "&appKey=" + appKey + "&utc=false";
		AirlineCode response = restTemplateWithUserAgent.getForObject(url, AirlineCode.class);
		return response.getFlightTracks();
	}

	public ArrayList<Airports> searchAirportCode() {

		String url = "https://api.flightstats.com/flex/flightstatus/rest/v2/json/airport/tracks/DTW/arr" + "?appId="
				+ appId + "&appKey=" + appKey + "&utc=false";
		AirlineCode response = restTemplateWithUserAgent.getForObject(url, AirlineCode.class);
		return response.getAppendix().getAirports();
	}
}
