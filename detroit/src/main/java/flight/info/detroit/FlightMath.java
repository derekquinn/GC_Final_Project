package flight.info.detroit;

public class FlightMath {

	private Long gateArrivalMetric;
	private Long runwayDepartureMetric;

	public FlightMath( ) {
		
	}
	
	public FlightMath(Long gateArrivalMetric, Long runwayDepartureMetric) {
		this.gateArrivalMetric = gateArrivalMetric;
		this.runwayDepartureMetric = runwayDepartureMetric;
	}
	public Long getGateArrivalMetric() {
		return gateArrivalMetric;
	}

	public void setGateArrivalMetric(Long gateArrivalMetric) {
		this.gateArrivalMetric = gateArrivalMetric;
	}

	public Long getRunwayDepartureMetric() {
		return runwayDepartureMetric;
	}

	public void setRunwayDepartureMetric(Long runwayDepartureMetric) {
		this.runwayDepartureMetric = runwayDepartureMetric;
	}
	
	@Override
	public String toString() {
		return "FlightMath [gateArrivalMetric=" + gateArrivalMetric + ", runwayDepartureMetric=" + runwayDepartureMetric +"]";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
