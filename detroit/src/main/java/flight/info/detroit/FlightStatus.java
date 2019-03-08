package flight.info.detroit;

public class FlightStatus {

	private Long flightId;
	private String carrierFsCode;
	private String flightNumber;
	private String departureAirportFsCode;
	private String arrivalAirportFsCode;
	private DepartureDate departureDate;
	private ArrivalDate arrivalDate;
	private String status;
	private OperationalTimes operationalTimes;
	private Integer flightDurations;
	private String airportResources;
	private String flightEquipment;

	public FlightStatus(Long flightId, String carrierFsCode, String flightNumber, String departureAirportFsCode,
			String arrivalAirportFsCode, DepartureDate departureDate, ArrivalDate arrivalDate, String status,
			OperationalTimes operationalTimes, Integer flightDurations, String airportResources,
			String flightEquipment) {
		super();
		this.flightId = flightId;
		this.carrierFsCode = carrierFsCode;
		this.flightNumber = flightNumber;
		this.departureAirportFsCode = departureAirportFsCode;
		this.arrivalAirportFsCode = arrivalAirportFsCode;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.status = status;
		this.operationalTimes = operationalTimes;
		this.flightDurations = flightDurations;
		this.airportResources = airportResources;
		this.flightEquipment = flightEquipment;
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
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

	public String getArrivalAirportFsCode() {
		return arrivalAirportFsCode;
	}

	public void setArrivalAirportFsCode(String arrivalAirportFsCode) {
		this.arrivalAirportFsCode = arrivalAirportFsCode;
	}

	public DepartureDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(DepartureDate departureDate) {
		this.departureDate = departureDate;
	}

	public ArrivalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(ArrivalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OperationalTimes getOperationalTimes() {
		return operationalTimes;
	}

	public void setOperationalTimes(OperationalTimes operationalTimes) {
		this.operationalTimes = operationalTimes;
	}

	public Integer getFlightDurations() {
		return flightDurations;
	}

	public void setFlightDurations(Integer flightDurations) {
		this.flightDurations = flightDurations;
	}

	public String getAirportResources() {
		return airportResources;
	}

	public void setAirportResources(String airportResources) {
		this.airportResources = airportResources;
	}

	public String getFlightEquipment() {
		return flightEquipment;
	}

	public void setFlightEquipment(String flightEquipment) {
		this.flightEquipment = flightEquipment;
	}

}
