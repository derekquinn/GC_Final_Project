package flight.info.detroit;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Appendix {

	private ArrayList<Airports> airports;
	private ArrayList<Airlines> airlines;

	public void setAirports(ArrayList<Airports> airports) {
		this.airports = airports;
	}

	public ArrayList<Airlines> getAirlines() {
		return airlines;
	}

	public ArrayList<Airports> getAirports() {
		return airports;
	}

	public void setAirlines(ArrayList<Airlines> airlines) {
		this.airlines = airlines;
	}

	@Override
	public String toString() {
		return "Appendix [airports=" + airports + "]";
	}

}