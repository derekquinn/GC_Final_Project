package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FlightMathCalculator {

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

		// String hoursAsString = Long.toString(hours);
		// String minutesAsString = Long.toString(minutes);
		// String hoursAndMinutes = "Difference is" + "HR " + hoursAsString + "MIN"+
		// minutesAsString;

		return totalMinutes;
	}

	public static Long runwayDepartureMath(FlightStatus fs) {

		String estimateTakeoff = fs.getOperationalTimes().getEstimatedRunwayDeparture().getDateLocal();
		String actualTakeoff = fs.getOperationalTimes().getActualRunwayDeparture().getDateLocal();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		
		LocalDateTime actual = LocalDateTime.parse(actualTakeoff, formatter);
		LocalDateTime estimated = LocalDateTime.parse(estimateTakeoff, formatter);
		
		LocalDateTime fromTemp = LocalDateTime.from(actual);
		
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
		
		//Not being used from the api, but place if the api useds it in future
		long millis = fromTemp.until(estimated, ChronoUnit.MILLIS);

		long hoursAsMinutes = hours * 60;

		long totalMinutes = minutes + hoursAsMinutes;

	
		return totalMinutes;
		
	}

}
