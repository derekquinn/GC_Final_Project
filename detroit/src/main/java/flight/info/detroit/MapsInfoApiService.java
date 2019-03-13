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
	public Long getTravelWithTraffic(String origin) {
		GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

		DistanceMatrix trix;
//		Instant now = new Instant(52329028);
		try {
			DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context);
			trix = req.origins(origin).destinations("11050 Rogell Drive, Romulus, MI").departureTime(Instant.now())
					.await();

			System.out.println(trix.rows[0].elements[0].duration.humanReadable);
			// System.out.println(trix.rows[0].elements[0].
			System.out.println("What?");
			Map<String, Integer> map = new HashMap<>();
			map.put("Hi", 3);
			map.put("Yo", 7);
		//	Duration dur;
//        System.out.println(trix);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(map));
			System.out.println(gson.toJson(trix));
			System.out.println("Hello?");
//			return trix;
			Long dur = trix.rows[0].elements[0].durationInTraffic.inSeconds;
			return dur;
		} catch (ApiException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
