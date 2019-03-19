package flight.info.detroit;

import java.util.ArrayList;

public class AirlineCode {

	private ArrayList<FlightTracks> flightTracks;

	private Appendix appendix;

	public Appendix getAppendix() {
		return appendix;
	}

	public void setAppendix(Appendix appendix) {
		this.appendix = appendix;
	}

	public ArrayList<FlightTracks> getFlightTracks() {
		return flightTracks;
	}

	public void setFlightTracks(ArrayList<FlightTracks> flightTracks) {
		this.flightTracks = flightTracks;
	}

	@Override
	public String toString() {
		return "AirlineCode [flightTracks=" + flightTracks + appendix + "]";
	}

}
