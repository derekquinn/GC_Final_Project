package flight.info.detroit;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

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
	private Long driveDurationSec;
	@Column(name = "departure_time")
	private LocalDateTime driverDeparture;
	@Column(name = "departure_time_fmt")
	private String fmtDriverDepartureTime;
	private String fmtPickupTime;
	private String fmtGateArrival;
	private String driverOrigin;
	private boolean hasBags;

	public FlightStatus() {

	}

	public FlightStatus(Long id, Long flightId, String carrierFsCode, Integer flightNumber,
			String departureAirportFsCode, String arrivalAirportFsCode, DepartureDate departureDate,
			ArrivalDate arrivalDate, String status, OperationalTimes operationalTimes, FlightDurations flightDurations,
			AirportResources airportResources, FlightEquipment flightEquipment, FlightMath flightMath,
			Long driveDurationSec, LocalDateTime driverDeparture, String fmtDriverDepartureTime, String driverOrigin, String fmtGateArrival,
			boolean hasBags) {
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
		this.driveDurationSec = driveDurationSec;
		this.fmtDriverDepartureTime = fmtDriverDepartureTime;
		this.driverOrigin = driverOrigin;
		this.fmtGateArrival = fmtGateArrival;
		this.hasBags = hasBags;
	}

	public FlightStatus(Long flightId, String carrierFsCode, Integer flightNumber, String departureAirportFsCode,
			String arrivalAirportFsCode, DepartureDate departureDate, ArrivalDate arrivalDate, String status,
			OperationalTimes operationalTimes, FlightDurations flightDurations, AirportResources airportResources,
			FlightEquipment flightEquipment, FlightMath flightMath, Long driveDurationSec,
			LocalDateTime driverDeparture, String fmtDriverDepartureTime, String driverOrigin, boolean hasBags) {
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
		this.driveDurationSec = driveDurationSec;
		this.driverDeparture = driverDeparture;
		this.fmtDriverDepartureTime = fmtDriverDepartureTime;
		this.driverOrigin = driverOrigin;
		this.hasBags = hasBags;
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

	public Long getDuration() {
		return driveDurationSec;
	}

	public void setDuration(Long duration) {
		this.driveDurationSec = duration;
	}

	public LocalDateTime getDriverDeparture() {
		return driverDeparture;
	}

	public void setDriverDeparture(LocalDateTime driverDeparture) {
		this.driverDeparture = driverDeparture;
	}

	public Long getDriveDurationSec() {
		return driveDurationSec;
	}

	public void setDriveDurationSec(Long driveDurationSec) {
		this.driveDurationSec = driveDurationSec;
	}

	public String getFmtDriverDepartureTime() {
		return fmtDriverDepartureTime;
	}

	public void setFmtDriverDepartureTime(String fmtDriverDepartureTime) {
		this.fmtDriverDepartureTime = fmtDriverDepartureTime;
	}

	public String getDriverOrigin() {
		return driverOrigin;
	}

	public void setDriveOrigin(String driverOrigin) {
		this.driverOrigin = driverOrigin;
	}

	public boolean getHasBags() {
		return hasBags;
	}

	public void setHasBags(boolean hasBags) {
		this.hasBags = hasBags;
	}
	

	public String getFmtPickupTime() {
		return fmtPickupTime;
	}

	public void setFmtPickupTime(String fmtPickupTime) {
		this.fmtPickupTime = fmtPickupTime;
	}

	public String getFmtGateArrival() {
		return fmtGateArrival;
	}

	public void setFmtGateArrival(String fmtGateArrival) {
		this.fmtGateArrival = fmtGateArrival;
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
