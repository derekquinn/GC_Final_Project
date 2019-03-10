package flight.info.detroit;

public class FlightMath {

	private Integer gateArrivalMetric;
	private Integer runwayDepartureMetric;

	public FlightMath( ) {
		
	}
	
	public FlightMath(Integer gateArrivalMetric, Integer runwayDepartureMetric) {
		this.gateArrivalMetric = gateArrivalMetric;
		this.runwayDepartureMetric = runwayDepartureMetric;
	}

	public Integer getGateArrivalMetric() {
		return gateArrivalMetric;
	}

	public void setGateArrivalMetric(Integer gateArrivalMetric) {
		this.gateArrivalMetric = gateArrivalMetric;
	}

	public Integer getRunwayDepartureMetric() {
		return runwayDepartureMetric;
	}

	public void setRunwayDepartureMetric(Integer runwayDepartureMetric) {
		this.runwayDepartureMetric = runwayDepartureMetric;
	}
	
	@Override
	public String toString() {
		return "FlightMath [gateArrivalMetric=" + gateArrivalMetric + ", runwayDepartureMetric=" + runwayDepartureMetric +"]";
	}


}
