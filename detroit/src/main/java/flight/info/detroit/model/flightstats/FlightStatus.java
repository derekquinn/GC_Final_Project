package flight.info.detroit.model.flightstats;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import flight.info.detroit.FlightMath;

@Entity
@Table(name = "flight_data")
public class FlightStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Transient
	private Long flightId;
	private String carrierFsCode;
	private Integer flightNumber;
	@Transient
	private String departureAirportFsCode;
	@Transient
	private String arrivalAirportFsCode;
	@Transient
	private DepartureDate departureDate;
	@Transient
	private ArrivalDate arrivalDate;
	@Transient
	private String status;
	@Transient
	private OperationalTimes operationalTimes;
	@Transient
	private FlightDurations flightDurations;
	@Transient
	private AirportResources airportResources;
	@Transient
	private FlightEquipment flightEquipment;
	@Transient
	private FlightMath flightMath;
	@Column(name = "duration")
	private Long driveDuration;
	@Column(name = "departure_time")
	private LocalDateTime driverDeparture;

	public FlightStatus() {

	}

	public FlightStatus(Long id, Long flightId, String carrierFsCode, Integer flightNumber,
			String departureAirportFsCode, String arrivalAirportFsCode, DepartureDate departureDate,
			ArrivalDate arrivalDate, String status, OperationalTimes operationalTimes, FlightDurations flightDurations,
			AirportResources airportResources, FlightEquipment flightEquipment, FlightMath flightMath,
			Long driveDuration, LocalDateTime driverDeparture) {
		super();
		this.id = id;
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
		this.flightMath = flightMath;
		this.driveDuration = driveDuration;
		this.driverDeparture = driverDeparture;
	}

	public FlightStatus(Long flightId, String carrierFsCode, Integer flightNumber, String departureAirportFsCode,
			String arrivalAirportFsCode, DepartureDate departureDate, ArrivalDate arrivalDate, String status,
			OperationalTimes operationalTimes, FlightDurations flightDurations, AirportResources airportResources,
			FlightEquipment flightEquipment, FlightMath flightMath) {
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
		this.flightMath = flightMath;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(Integer flightNumber) {
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

	public FlightDurations getFlightDurations() {
		return flightDurations;
	}

	public void setFlightDurations(FlightDurations flightDurations) {
		this.flightDurations = flightDurations;
	}

	public AirportResources getAirportResources() {
		return airportResources;
	}

	public void setAirportResources(AirportResources airportResources) {
		this.airportResources = airportResources;
	}

	public FlightEquipment getFlightEquipment() {
		return flightEquipment;
	}

	public void setFlightEquipment(FlightEquipment flightEquipment) {
		this.flightEquipment = flightEquipment;
	}

	public FlightMath getFlightMath() {
		return flightMath;
	}

	public void setFlightMath(FlightMath flightMath) {
		this.flightMath = flightMath;
	}

	public Long getDriveDuration() {
		return driveDuration;
	}

	public void setDriveDuration(Long driveDuration) {
		this.driveDuration = driveDuration;
	}

	public LocalDateTime getDriverDeparture() {
		return driverDeparture;
	}

	public void setDriverDeparture(LocalDateTime driverDeparture) {
		this.driverDeparture = driverDeparture;
	}

	@Override
	public String toString() {
		return "FlightStatus [flightId=" + flightId + ", carrierFsCode=" + carrierFsCode + ", flightNumber="
				+ flightNumber + ", departureAirportFsCode=" + departureAirportFsCode + ", arrivalAirportFsCode="
				+ arrivalAirportFsCode + ", departureDate=" + departureDate + ", arrivalDate=" + arrivalDate
				+ ", status=" + status + ", operationalTimes=" + operationalTimes + ", flightDurations="
				+ flightDurations + ", airportResources=" + airportResources + ", flightEquipment=" + flightEquipment
				+ ", flightMath=" + flightMath + "]";
	}

}
