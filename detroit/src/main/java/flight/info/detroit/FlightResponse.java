package flight.info.detroit;

import java.util.List;

public class FlightResponse {
	private List<FlightStatus> flightStatuses;
	

	public List<FlightStatus> getFlightStatuses() {
		return flightStatuses;
	}

	public void setFlightStatuses(List<FlightStatus> flightStatuses) {
		this.flightStatuses = flightStatuses;
	}


	
}
