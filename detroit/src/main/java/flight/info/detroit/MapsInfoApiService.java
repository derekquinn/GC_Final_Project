package flight.info.detroit;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.Duration;

@Component
public class MapsInfoApiService {

	@Value("${googlematrix.apikey}")
	String apiKey;

	/*
	 * private RestTemplate restTemplate = new RestTemplate();
	 * 
	 * public List<destination> getAll(){ String url =
	 * "https://app.ticketmaster.com/discovery/v2/events.json?countryCode=US&page=0&size=100&apikey="
	 * + apikey; ApiResponse apiResponse = restTemplate.getForObject(url,
	 * ApiResponse.class); return apiResponse.getEmbedded().getEvents(); }
	 */

	/*
	 * public void test() { GeoApiContext context = new GeoApiContext.Builder()
	 * .apiKey(apikey) .build(); GeocodingResult[] results =
	 * GeocodingApi.geocode(context,
	 * "1600 Amphitheatre Parkway Mountain View, CA 94043").await(); Gson gson = new
	 * GsonBuilder().setPrettyPrinting().create();
	 * System.out.println(gson.toJson(results[0].addressComponents)); }
	 */
	// origin = "1 Park Ave, Detroit, MI";
	public Long getTravelWithTraffic(String origin, String arrivalTerminal) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();
		
		DistanceMatrix trix;
//		Instant now = new Instant(52329028);
		// if arrival terminal contains an 'M", or the arrival terminal cannot be determined, use the coordinates
		// for DTW McNamara Terminal to calculate drive time. Otherwise, use the coordinates for DTW North Terminal.
		System.out.println("Arrival Terminal is" + arrivalTerminal);
		
		if (arrivalTerminal == null || arrivalTerminal.isEmpty() || arrivalTerminal.contains("M") ) {
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			trix = req.origins(origin).destinations("42.2073831,-83.356775").departureTime(Instant.now())
					.await();

			System.out.println(trix.rows[0].elements[0].duration.humanReadable);

			System.out.println("You are driving to McNamara Terminal");
			Long dur;
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
			dur = trix.rows[0].elements[0].durationInTraffic.inSeconds;
			return dur;
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else {
			try {
				DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
				trix = req.origins(origin).destinations("42.225302, -83.348186").departureTime(Instant.now())
						.await();

				System.out.println(trix.rows[0].elements[0].duration.humanReadable);

				System.out.println("You are driving to North Terminal");
				Long dur;

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
				dur = trix.rows[0].elements[0].durationInTraffic.inSeconds;
				return dur;
			} catch (ApiException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}
		return null;
	}
}