package flight.info.detroit;

public class FlightEquipment {

	private String scheduledEquipmentIataCode;
	private String actualEquipmentIataCode;
	private String tailNumber;

	public FlightEquipment() {

	}

	public FlightEquipment(String scheduledEquipmentIataCode, String actualEquipmentIataCode, String tailNumber) {
		super();
		this.scheduledEquipmentIataCode = scheduledEquipmentIataCode;
		this.actualEquipmentIataCode = actualEquipmentIataCode;
		this.tailNumber = tailNumber;
	}

	public String getScheduledEquipmentIataCode() {
		return scheduledEquipmentIataCode;
	}

	public void setScheduledEquipmentIataCode(String scheduledEquipmentIataCode) {
		this.scheduledEquipmentIataCode = scheduledEquipmentIataCode;
	}

	public String getActualEquipmentIataCode() {
		return actualEquipmentIataCode;
	}

	public void setActualEquipmentIataCode(String actualEquipmentIataCode) {
		this.actualEquipmentIataCode = actualEquipmentIataCode;
	}

	public String getTailNumber() {
		return tailNumber;
	}

	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}

	@Override
	public String toString() {
		return "FlightEquipment [scheduledEquipmentIataCode=" + scheduledEquipmentIataCode
				+ ", actualEquipmentIataCode=" + actualEquipmentIataCode + ", tailNumber=" + tailNumber + "]";
	}

}
