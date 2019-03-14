package flight.info.detroit.model.flightstats;

public class DepartureDate {

	private String dateLocal;
	private String dateUtc;

	public DepartureDate() {

	}

	public DepartureDate(String dateLocal, String dateUtc) {
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
		return "DepartureDate [dateLocal=" + dateLocal + ", dateUtc=" + dateUtc + "]";
	}

}
