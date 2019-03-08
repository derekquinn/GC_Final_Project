package flight.info.detroit;

public class ArrivalDate {
	private String dateLocal;
	private String dateUtc;

	public ArrivalDate() {

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

	public ArrivalDate(String dateLocal, String dateUtc) {
		super();
		this.dateLocal = dateLocal;
		this.dateUtc = dateUtc;
	}
}
