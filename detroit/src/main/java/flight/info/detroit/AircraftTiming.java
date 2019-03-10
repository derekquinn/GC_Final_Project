package flight.info.detroit;

import java.time.LocalDateTime;

public class AircraftTiming {
	private LocalDateTime dateLocal;
	private String dateUtc;

	public AircraftTiming() {

	}

	public AircraftTiming(LocalDateTime dateLocal, String dateUtc) {
		super();
		this.dateLocal = dateLocal;
		this.dateUtc = dateUtc;
	}

	public LocalDateTime getDateLocal() {
		return dateLocal;
	}

	public void setDateLocal(LocalDateTime dateLocal) {
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
