package flight.info.detroit;

public class FlightTracks {

	private String carrierFsCode;
	private String flightNumber;
	private String departureAirportFsCode;
	private DepartureDate departureDate;

	public DepartureDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(DepartureDate departureDate) {
		this.departureDate = departureDate;
	}

	public String getCarrierFsCode() {
		return carrierFsCode;
	}

	public void setCarrierFsCode(String carrierFsCode) {
		this.carrierFsCode = carrierFsCode;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDepartureAirportFsCode() {
		return departureAirportFsCode;
	}

	public void setDepartureAirportFsCode(String departureAirportFsCode) {
		this.departureAirportFsCode = departureAirportFsCode;
	}

	@Override
	public String toString() {
		return "FlightTracks [carrierFsCode=" + carrierFsCode + ", flightNumber=" + flightNumber
				+ ", departureAirportFsCode=" + departureAirportFsCode + "]";
	}

}
