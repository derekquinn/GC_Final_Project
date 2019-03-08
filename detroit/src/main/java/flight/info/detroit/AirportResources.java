package flight.info.detroit;

public class AirportResources {
	private String departureTerminal;
	private String departureGate;
	private String arrivalTerminal;

	public AirportResources() {

	}

	public AirportResources(String departureTerminal, String departureGate, String arrivalTerminal) {
		super();
		this.departureTerminal = departureTerminal;
		this.departureGate = departureGate;
		this.arrivalTerminal = arrivalTerminal;
	}

	public String getDepartureTerminal() {
		return departureTerminal;
	}

	public void setDepartureTerminal(String departureTerminal) {
		this.departureTerminal = departureTerminal;
	}

	public String getDepartureGate() {
		return departureGate;
	}

	public void setDepartureGate(String departureGate) {
		this.departureGate = departureGate;
	}

	public String getArrivalTerminal() {
		return arrivalTerminal;
	}

	public void setArrivalTerminal(String arrivalTerminal) {
		this.arrivalTerminal = arrivalTerminal;
	}

}
