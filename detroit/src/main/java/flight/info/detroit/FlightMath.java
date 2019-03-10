package flight.info.detroit;

import java.time.LocalDateTime;

public class FlightMath implements Comparable<LocalDateTime>{

	private Integer gateArrivalMetric;
	private Integer runwayDepartureMetric;
	private FlightStatus flightStatus;


	public FlightMath(Integer gateArrivalMetric, Integer runwayDepartureMetric) {
// math to determine gate Arrival Metric based on Operational Times from Flight Status class
//		String estimatedGateArrival = flightStatus.getOperationalTimes().getEstimatedGateArrival().getDateUtc();
//		String estimatedGateArrivalMinutesSub = estimatedGateArrival.substring(13, 15); 
//		Integer estimatedGateArrivalMinutes = Integer.parseInt(estimatedGateArrivalMinutesSub);

		LocalDateTime estimatedGateArrival = flightStatus.getOperationalTimes().getEstimatedGateArrival().getDateLocal();
		LocalDateTime actualGateArrival = flightStatus.getOperationalTimes().getActualGateArrival().getDateLocal();

		this.gateArrivalMetric = estimatedGateArrival.compareTo(actualGateArrival);
	
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

	@Override
	public int compareTo(LocalDateTime o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "FlightMath [gateArrivalMetric=" + gateArrivalMetric + ", runwayDepartureMetric=" + runwayDepartureMetric
				+ ", flightStatus=" + flightStatus + "]";
	}


}
