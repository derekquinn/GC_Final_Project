package flight.info.detroit;


public class FlightMath {

	private Integer gateArrivalMetric;
	private Integer runwayDepartureMetric;
	private FlightStatus flightStatus;

	public FlightMath(Integer gateArrivalMetric, Integer runwayDepartureMetric) {
		
		String estimatedGateArrival = flightStatus.getOperationalTimes().getEstimatedGateArrival().getDateUtc();
		String estimatedGateArrivalMinutes = estimatedGateArrival.substring(15, 17); 
	
		this.gateArrivalMetric = gateArrivalMetric;
// set the metric equal to estimated arrival - actual arrival 
				

				
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

}
