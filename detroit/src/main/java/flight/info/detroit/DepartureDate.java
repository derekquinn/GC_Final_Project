package flight.info.detroit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DepartureDate {

	private String dateLocal;
	private String dateUtc;
	String formattedDate;

	public DepartureDate() {

	}

	public DepartureDate(String dateLocal, String dateUtc) {
		super();
		this.dateLocal = dateLocal;
		this.dateUtc = dateUtc;
	}

	public String getDateLocal() {
		String estimatedGateArrival = dateLocal;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
		LocalDateTime gateArrival = LocalDateTime.parse(estimatedGateArrival, formatter);
		dateLocal = gateArrival.toLocalTime().format(DateTimeFormatter.ofPattern("hh:mm a"));
		return dateLocal;
	}

	public void setDateLocal(String dateLocal) {
		this.dateLocal = dateLocal;
	}

	public String getDateUtc() {
		
		return dateUtc;
	}

	public void setDateUtc(String dateUtc) {
		this.dateUtc = dateUtc;
	}

	@Override
	public String toString() {
		return "DepartureDate [dateLocal=" + dateLocal + ", dateUtc=" + dateUtc + "]";
	}

}
