package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class FlightMathCalculator {

	private static Long getBagsTime = 10L;
	private static Long getWalkToDoor = 0L;

	// determine how late / early to expect a plane based on flight stats API
	// estimates
	public static Long gateArrivalMath(FlightStatus fs) {

		String publishedArrival = fs.getOperationalTimes().getPublishedArrival().getDateLocal();
		String estimatedGateArrival = fs.getOperationalTimes().getEstimatedGateArrival().getDateLocal();
		String actualGateArrival = fs.getOperationalTimes().getActualGateArrival().getDateLocal();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

		LocalDateTime published = LocalDateTime.parse(publishedArrival, formatter);
		LocalDateTime estimated = LocalDateTime.parse(estimatedGateArrival, formatter);
		LocalDateTime actual = LocalDateTime.parse(actualGateArrival, formatter);

		if (actual == null) {

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

		} else {

			LocalDateTime fromTemp = LocalDateTime.from(published);
			long years = fromTemp.until(actual, ChronoUnit.YEARS);
			fromTemp = fromTemp.plusYears(years);

			long months = fromTemp.until(actual, ChronoUnit.MONTHS);
			fromTemp = fromTemp.plusMonths(months);

			long days = fromTemp.until(actual, ChronoUnit.DAYS);
			fromTemp = fromTemp.plusDays(days);

			long hours = fromTemp.until(estimated, ChronoUnit.HOURS);
			fromTemp = fromTemp.plusHours(hours);

			long minutes = fromTemp.until(actual, ChronoUnit.MINUTES);
			fromTemp = fromTemp.plusMinutes(minutes);

			long seconds = fromTemp.until(actual, ChronoUnit.SECONDS);
			fromTemp = fromTemp.plusSeconds(seconds);

			long millis = fromTemp.until(actual, ChronoUnit.MILLIS);

			long hoursAsMinutes = hours * 60;

			long totalMinutes = minutes + hoursAsMinutes;

			return totalMinutes;
		}
	}

	// assign departure time for driver in bagless scenario
	public static LocalDateTime driverDepartureWithBags(FlightStatus fs, Long durationInSeconds) {

		String estimatedGateArrival = fs.getOperationalTimes().getScheduledGateArrival().getDateLocal();
		String actualGateArrival = null;

		try {
			actualGateArrival = fs.getOperationalTimes().getActualGateArrival().getDateLocal();
		} catch (NullPointerException e) {
			// it's okay for one of these to be null
		}

		if (actualGateArrival == null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime estimated = LocalDateTime.parse(estimatedGateArrival, formatter);
			Long minsInTraffic = durationInSeconds / 60;

			Long airlinePassTask = getBagsTime;

			LocalDateTime timeAtDoor = estimated.plusMinutes(airlinePassTask);
			LocalDateTime timeToLeave = timeAtDoor.minusMinutes(minsInTraffic);

			return timeToLeave;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime actual = LocalDateTime.parse(actualGateArrival, formatter);
			Long minsInTraffic = durationInSeconds / 60;

			Long airlinePassTask = getBagsTime;

			LocalDateTime timeAtDoor = actual.plusMinutes(airlinePassTask);
			LocalDateTime timeToLeave = timeAtDoor.minusMinutes(minsInTraffic);

			return timeToLeave;
		}

	}

	// account for baggage claim
	public static LocalDateTime driverDepartureNoBags(FlightStatus fs, Long durationInSeconds) {

		String estimatedGateArrival = fs.getOperationalTimes().getScheduledGateArrival().getDateLocal();
		String actualGateArrival = null;

		try {
			actualGateArrival = fs.getOperationalTimes().getActualGateArrival().getDateLocal();
		} catch (NullPointerException e) {
			// it's okay for one of these to be null
		}

		if (actualGateArrival == null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime estimated = LocalDateTime.parse(estimatedGateArrival, formatter);
			Long minsInTraffic = durationInSeconds / 60;

			Long airlinePassTask = getWalkToDoor;

			LocalDateTime timeAtDoor = estimated.plusMinutes(airlinePassTask);
			LocalDateTime timeToLeave = timeAtDoor.minusMinutes(minsInTraffic);

			return timeToLeave;
		}

		else {

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime actual = LocalDateTime.parse(actualGateArrival, formatter);
			Long minsInTraffic = durationInSeconds / 60;

			Long airlinePassTask = getWalkToDoor;

			LocalDateTime timeAtDoor = actual.plusMinutes(airlinePassTask);
			LocalDateTime timeToLeave = timeAtDoor.minusMinutes(minsInTraffic);

			return timeToLeave;
		}

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

		// String driverDeparture = fs.getFmtDriverDepartureTime();

		String pickupTime = fs.getFmtPickupTime();

		// LocalTime driverDepartureFmt = LocalTime.parse(driverDeparture, formatter);
		LocalTime currentTime = LocalTime.now();
		System.out.println(currentTime);
		LocalTime pickupTimeFmt = LocalTime.parse(pickupTime, formatter);

		Long progressMetric = ChronoUnit.MINUTES.between(currentTime, pickupTimeFmt);

		return progressMetric;
	}

	// when TRUE is returned the pickup stage on TIMELINE should be checked
	public static boolean PickupStageComplete(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");

		LocalTime currentTime = LocalTime.now();
		LocalTime timeMetric1 = LocalTime.parse(time, formatter);

		return timeMetric1.isBefore(currentTime);
	}

	// check to see if aircraft is JUMBO JET or REGIONAL JET
	public static Long checkPlaneSize(FlightStatus fs) {
		Long planeSizeAdjustment = 10L;
		String planeSize = fs.getFlightEquipment().getScheduledEquipmentIataCode();
		// small plane decrease in time
		if (planeSize.equals("CR9") || planeSize.equals("CR7") || planeSize.equals("CRJ") || planeSize.equals("CR2")) {
			planeSizeAdjustment = planeSizeAdjustment - 10L;
			// bigger planes
		} else if (planeSize.equals("333") || planeSize.equals("752") || planeSize.equals("753")) {
			planeSizeAdjustment = planeSizeAdjustment + 10L;
		} // biggest planes
		else if (planeSize.equals("359") || planeSize.equals("77W") || planeSize.equals("788")) {
			planeSizeAdjustment = planeSizeAdjustment + 15L;
		}
		return planeSizeAdjustment;
	}

	public static Long checkGateWalkTime(FlightStatus fs) {

		Long gateWalkAdjustment = 0L;
		String arrivalGate = "";
		String arrivalTerminal = "";

		try {
			arrivalGate = fs.getAirportResources().getArrivalGate();

		} catch (NullPointerException e) {

		}

		try {
			arrivalTerminal = fs.getAirportResources().getArrivalTerminal();

		} catch (NullPointerException e) {

		}

		if (arrivalTerminal.startsWith("M")) {
			if (!(arrivalGate.equals("A7"))
					&& ((arrivalGate.startsWith("B") || arrivalGate.startsWith("A7") || arrivalGate.startsWith("C")))) {
				gateWalkAdjustment = gateWalkAdjustment + 15L;
			} else if (!(arrivalGate.equals("A6")) && ((arrivalGate.startsWith("A6") || arrivalGate.startsWith("A1")
					|| arrivalGate.startsWith("A2")))) {
				gateWalkAdjustment = gateWalkAdjustment + 10L;
			} else {

				gateWalkAdjustment = 10L;
			}

		}

		if (arrivalTerminal.startsWith("N") || arrivalGate.startsWith("D")) {
			gateWalkAdjustment = gateWalkAdjustment + 5L;
		}

		else if (arrivalGate.equals("D1") || arrivalGate.equals("D2") || arrivalGate.equals("D3")
				|| arrivalGate.equals("D4") || arrivalGate.equals("D5") || arrivalGate.equals("D6")
				|| arrivalGate.equals("D28") || arrivalGate.equals("D30") || arrivalGate.equals("D32")) {
			gateWalkAdjustment = gateWalkAdjustment + 8L;

		} else {

			gateWalkAdjustment = 11L;
		}

		return gateWalkAdjustment;
	}

	public static String humanReadableDuration(Long duration) {
		Long driveTimeMinutesComponent;
		Long driveTimeSecondsComponent;
		String driveTimeHumanReadable;

		driveTimeMinutesComponent = duration / 60;
		driveTimeSecondsComponent = duration % 60;
		driveTimeHumanReadable = driveTimeMinutesComponent + " minutes " + driveTimeSecondsComponent + " seconds";

		return driveTimeHumanReadable;
	}

}