package flight.info.detroit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;

@Component
public class MapsInfoApiService {

	@Value("${googlematrix.apikey}")
	String apikey;
	
	/*
	private RestTemplate restTemplate = new RestTemplate();

	public List<destination> getAll(){
		String url = "https://app.ticketmaster.com/discovery/v2/events.json?countryCode=US&page=0&size=100&apikey=" + apikey;
		ApiResponse apiResponse = restTemplate.getForObject(url, ApiResponse.class);
		return apiResponse.getEmbedded().getEvents();
	}
	*/
	
	/*
	public void test() {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(apikey)
			    .build();
			GeocodingResult[] results =  GeocodingApi.geocode(context,
			    "1600 Amphitheatre Parkway Mountain View, CA 94043").await();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			System.out.println(gson.toJson(results[0].addressComponents));
	}
	*/
	
	public void printDistanceMatrix() {
		GeoApiContext context = new GeoApiContext.Builder()
			    .apiKey(apikey)
			    .build();
    try {
        DistanceMatrixApiRequest req = DistanceMatrixApi.newRequest(context); 
        DistanceMatrix trix = req.origins("Vancouver BC","Seattle")
                .destinations("San Francisco","Victoria BC")
                .await();
        System.out.println(trix);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
		System.out.println(gson.toJson(trix));
    } catch(ApiException e){
        e.printStackTrace();
    } catch(Exception e){
        System.out.println(e.getMessage());
    } 
	}
}
