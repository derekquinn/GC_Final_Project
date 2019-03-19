package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FlightMathCalculator {

	private static Long getBagsTime = 22L;
	private static Long getWalkToDoor = 15L;

	// determine how late / early to expect a plane based on flight stats API estimates
	public static Long gateArrivalMath(FlightStatus fs) {

		String publishedArrival = fs.getOperationalTimes().getPublishedArrival().getDateLocal();
		String estimatedGateArrival = fs.getOperationalTimes().getEstimatedGateArrival().getDateLocal();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

		LocalDateTime published = LocalDateTime.parse(publishedArrival, formatter);
		LocalDateTime estimated = LocalDateTime.parse(estimatedGateArrival, formatter);

		LocalDateTime fromTemp = LocalDateTime.from(published);
		long years = fromTemp.until(estimated, ChronoUnit.YEARS);
		fromTemp = fromTemp.plusYears(years);

		long months = fromTemp.until(estimated, ChronoUnit.MONTHS);
		fromTemp = fromTemp.plusMonths(months);

		long days = fromTemp.until(estimated, ChronoUnit.DAYS);
		fromTemp = fromTemp.plusDays(days);

		long hours = fromTemp.until(estimated, ChronoUnit.HOURS);
		fromTemp = fromTemp.plusHours(hours);

		long minutes = fromTemp.until(estimated, ChronoUnit.MINUTES);
		fromTemp = fromTemp.plusMinutes(minutes);

		long seconds = fromTemp.until(estimated, ChronoUnit.SECONDS);
		fromTemp = fromTemp.plusSeconds(seconds);

		long millis = fromTemp.until(estimated, ChronoUnit.MILLIS);

		long hoursAsMinutes = hours * 60;

		long totalMinutes = minutes + hoursAsMinutes;

		return totalMinutes;
	}
	
	// assign departure time for driver in bagless scenario 
	public static LocalDateTime driverDepartureWithBags(FlightStatus fs, Long durationInSeconds) {

		String estimatedGateArrival = fs.getOperationalTimes().getScheduledGateArrival().getDateLocal();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime estimated = LocalDateTime.parse(estimatedGateArrival, formatter);
		Long minsInTraffic = durationInSeconds / 60;

		Long airlinePassTask = (getBagsTime + getWalkToDoor);

		LocalDateTime timeAtDoor = estimated.plusMinutes(airlinePassTask);
		LocalDateTime timeToLeave = timeAtDoor.minusMinutes(minsInTraffic);
		
		

		return timeToLeave;
	}
	// account for baggage cliam 
	public static LocalDateTime driverDepartureNoBags(FlightStatus fs, Long durationInSeconds) {

		String estimatedGateArrival = fs.getOperationalTimes().getScheduledGateArrival().getDateLocal();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime estimated = LocalDateTime.parse(estimatedGateArrival, formatter);
		Long minsInTraffic = durationInSeconds / 60;

		Long airlinePassTask = (getWalkToDoor);

		LocalDateTime timeAtDoor = estimated.plusMinutes(airlinePassTask);
		LocalDateTime timeToLeave = timeAtDoor.minusMinutes(minsInTraffic);

		return timeToLeave;
	}
	
	// calculate pickup time from google API and format it for humans
	public static String getPickupTime(Long driveTimeInSeconds, LocalDateTime driverDepartureTime) {

		Long minsInTraffic = driveTimeInSeconds / 60;

		LocalDateTime pickupTime = driverDepartureTime.plusMinutes(minsInTraffic);

		String formattedPickupTime = pickupTime.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

		return formattedPickupTime;

	}
	
	// calculate pickup time from google API and format it for timeline sorting
		public static LocalDateTime getPickupTimeLdt(Long driveTimeInSeconds, LocalDateTime driverDepartureTime) {

			Long minsInTraffic = driveTimeInSeconds / 60;

			LocalDateTime pickupTime = driverDepartureTime.plusMinutes(minsInTraffic);

			return pickupTime;

		}
	
	// get gate arrival time from flightstats API and format it for humans
	public static String getFormattedGateArrival(FlightStatus fs) {

		String gateArrivaljson = fs.getOperationalTimes().getScheduledGateArrival().getDateLocal();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime gateArrival = LocalDateTime.parse(gateArrivaljson, formatter);

		String formattedGateArrival = gateArrival.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));

		return formattedGateArrival;
	}
	
	// calculate percentage for progress bar on details page 
	
	public static Long getProgressBarMetric(FlightStatus fs) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		
	//	String driverDeparture = fs.getFmtDriverDepartureTime();
		
		String pickupTime = fs.getFmtPickupTime();
		
		//LocalTime driverDepartureFmt = LocalTime.parse(driverDeparture, formatter);
		LocalTime currentTime = LocalTime.now();
		System.out.println(currentTime);
		LocalTime pickupTimeFmt = LocalTime.parse(pickupTime, formatter);
		
		Long progressMetric = ChronoUnit.MINUTES.between(currentTime,pickupTimeFmt);
		
		return progressMetric;
	}
	
	// when TRUE is returned the pickup stage on TIMELINE should be checked
	public static boolean PickupStageComplete(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		
		LocalTime currentTime = LocalTime.now();
		LocalTime timeMetric1 = LocalTime.parse(time, formatter);

		return timeMetric1.isBefore(currentTime);
	}
}