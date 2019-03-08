package flight.info.detroit;

public class AirportResources {
	private String departureTerminal;
	private String departureGate;
	private String arrivalTerminal;
	private String arrivalGate;

	public AirportResources() {

	}

	public AirportResources(String departureTerminal, String departureGate, String arrivalTerminal,
			String arrivalGate) {
		super();
		this.departureTerminal = departureTerminal;
		this.departureGate = departureGate;
		this.arrivalTerminal = arrivalTerminal;
		this.arrivalGate = arrivalGate;
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

	public String getArrivalGate() {
		return arrivalGate;
	}

	public void setArrivalGate(String arrivalGate) {
		this.arrivalGate = arrivalGate;
	}

	@Override
	public String toString() {
		return "AirportResources [departureTerminal=" + departureTerminal + ", departureGate=" + departureGate
				+ ", arrivalTerminal=" + arrivalTerminal + ", arrivalGate=" + arrivalGate + "]";
	}

}
