package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FlightMathCalculator {

	public static String gateArrivalMath(FlightStatus fs) {


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

		String hoursAsString = Long.toString(hours);
		String minutesAsString = Long.toString(minutes);
		String hoursAndMinutes = "Difference is" + "HR " + hoursAsString + "MIN"+ minutesAsString;
				 

		return hoursAndMinutes;
	}
}


