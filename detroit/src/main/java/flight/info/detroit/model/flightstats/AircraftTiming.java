package flight.info.detroit.model.flightstats;

import java.time.LocalDateTime;

public class AircraftTiming {
	private String dateLocal;
	private String dateUtc;

	public AircraftTiming() {

	}

	public AircraftTiming(String dateLocal, String dateUtc) {
		super();
		this.dateLocal = dateLocal;
		this.dateUtc = dateUtc;
	}

	public String getDateLocal() {
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
		return "AircraftTiming [dateLocal=" + dateLocal + ", dateUtc=" + dateUtc + "]";
	}

}
