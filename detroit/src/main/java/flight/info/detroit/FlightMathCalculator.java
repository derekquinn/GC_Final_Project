package flight.info.detroit;

import java.time.LocalDateTime;

public class FlightMathCalculator  {
//implements Comparable<LocalDateTime>
	public static int getMinuteDifference() {
	FlightStatus flightStatus = new FlightStatus();
	LocalDateTime estimatedGateArrival = flightStatus.getOperationalTimes().getEstimatedGateArrival().getDateLocal();
	LocalDateTime actualGateArrival = flightStatus.getOperationalTimes().getActualGateArrival().getDateLocal();
	int minuteDifference = estimatedGateArrival.getMinute() - actualGateArrival.getMinute();
	int hourDifference = estimatedGateArrival.getHour() - actualGateArrival.getHour();
	
	//estimatedGateArrival.compareTo(actualGateArrival);
	//FlightMath.setGateArrivalMetric();
	return minuteDifference;
	}
	/*public int compareTo(LocalDateTime o) {
		int timeDiff = estimatedGateArrival.getMinute() - actualGateArrival.getMinute();
		return timeDiff;
	} */
	
	
}
