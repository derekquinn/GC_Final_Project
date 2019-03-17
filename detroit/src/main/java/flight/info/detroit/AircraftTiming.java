package flight.info.detroit;

import java.time.LocalDateTime;

import javax.persistence.Embedded;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

public class AircraftTiming {
	@Type(type = "LocalDateString")
	private String dateLocal;
	@Transient
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
