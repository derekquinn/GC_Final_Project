package flight.info.detroit;

public class FlightDurations {

	private Integer scheduledBlockMinutes;
	private Integer blockMinutes;
	private Integer scheduledAirMinutes;
	private Integer airMinutes;
	private Integer scheduledTaxiOutMinutes;
	private Integer taxiOutMinutes;
	private Integer scheduledTaxiInMinutes;
	private Integer taxiInMinutes;

	public FlightDurations() {

	}

	public FlightDurations(Integer scheduledBlockMinutes, Integer blockMinutes, Integer scheduledAirMinutes,
			Integer airMinutes, Integer scheduledTaxiOutMinutes, Integer taxiOutMinutes, Integer scheduledTaxiInMinutes,
			Integer taxiInMinutes) {
		super();
		this.scheduledBlockMinutes = scheduledBlockMinutes;
		this.blockMinutes = blockMinutes;
		this.scheduledAirMinutes = scheduledAirMinutes;
		this.airMinutes = airMinutes;
		this.scheduledTaxiOutMinutes = scheduledTaxiOutMinutes;
		this.taxiOutMinutes = taxiOutMinutes;
		this.scheduledTaxiInMinutes = scheduledTaxiInMinutes;
		this.taxiInMinutes = taxiInMinutes;
	}

	public Integer getScheduledBlockMinutes() {
		return scheduledBlockMinutes;
	}

	public void setScheduledBlockMinutes(Integer scheduledBlockMinutes) {
		this.scheduledBlockMinutes = scheduledBlockMinutes;
	}

	public Integer getBlockMinutes() {
		return blockMinutes;
	}

	public void setBlockMinutes(Integer blockMinutes) {
		this.blockMinutes = blockMinutes;
	}

	public Integer getScheduledAirMinutes() {
		return scheduledAirMinutes;
	}

	public void setScheduledAirMinutes(Integer scheduledAirMinutes) {
		this.scheduledAirMinutes = scheduledAirMinutes;
	}

	public Integer getAirMinutes() {
		return airMinutes;
	}

	public void setAirMinutes(Integer airMinutes) {
		this.airMinutes = airMinutes;
	}

	public Integer getScheduledTaxiOutMinutes() {
		return scheduledTaxiOutMinutes;
	}

	public void setScheduledTaxiOutMinutes(Integer scheduledTaxiOutMinutes) {
		this.scheduledTaxiOutMinutes = scheduledTaxiOutMinutes;
	}

	public Integer getTaxiOutMinutes() {
		return taxiOutMinutes;
	}

	public void setTaxiOutMinutes(Integer taxiOutMinutes) {
		this.taxiOutMinutes = taxiOutMinutes;
	}

	public Integer getScheduledTaxiInMinutes() {
		return scheduledTaxiInMinutes;
	}

	public void setScheduledTaxiInMinutes(Integer scheduledTaxiInMinutes) {
		this.scheduledTaxiInMinutes = scheduledTaxiInMinutes;
	}

	public Integer getTaxiInMinutes() {
		return taxiInMinutes;
	}

	public void setTaxiInMinutes(Integer taxiInMinutes) {
		this.taxiInMinutes = taxiInMinutes;
	}

	@Override
	public String toString() {
		return "FlightDurations [scheduledBlockMinutes=" + scheduledBlockMinutes + ", blockMinutes=" + blockMinutes
				+ ", scheduledAirMinutes=" + scheduledAirMinutes + ", airMinutes=" + airMinutes
				+ ", scheduledTaxiOutMinutes=" + scheduledTaxiOutMinutes + ", taxiOutMinutes=" + taxiOutMinutes
				+ ", scheduledTaxiInMinutes=" + scheduledTaxiInMinutes + ", taxiInMinutes=" + taxiInMinutes + "]";
	}

}
