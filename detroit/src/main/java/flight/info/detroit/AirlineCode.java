package flight.info.detroit;

import java.util.ArrayList;

public class AirlineCode {

	private ArrayList<FlightTracks> flightTracks;

	public ArrayList<FlightTracks> getFlightTracks() {
		return flightTracks;
	}

	public void setFlightTracks(ArrayList<FlightTracks> flightTracks) {
		this.flightTracks = flightTracks;
	}

	@Override
	public String toString() {
		return "AirlineCode [flightTracks=" + flightTracks + "]";
	}

}
