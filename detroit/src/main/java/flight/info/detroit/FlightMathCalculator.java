package flight.info.detroit;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FlightMathCalculator {

	private static Long getBagsTime = 22L;
	private static Long getWalkToDoor = 15L;

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
}