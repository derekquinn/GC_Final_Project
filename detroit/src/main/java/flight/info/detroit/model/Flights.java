package flight.info.detroit.model;

public class Flights {

	private Long id;
	private Long estimatedarrivaltime;
	private Long actualdeparturetime;
	private Long actualarrivaltime;
	private Long filed_departuretime;
	private String originCity;
	private String originName;
	private String destinationCity;
	private String destinationName;
	private String diverted;
	private String aircrafttype;
	private String ident;

	public Flights() {

	}

	public Flights(Long id, Long estimatedarrivaltime, Long actualdeparturetime, Long actualarrivaltime,
			Long filed_departuretime, String originCity, String originName, String destinationCity,
			String destinationName, String diverted, String aircrafttype, String ident) {
		super();
		this.id = id;
		this.estimatedarrivaltime = estimatedarrivaltime;
		this.actualdeparturetime = actualdeparturetime;
		this.actualarrivaltime = actualarrivaltime;
		this.filed_departuretime = filed_departuretime;
		this.originCity = originCity;
		this.originName = originName;
		this.destinationCity = destinationCity;
		this.destinationName = destinationName;
		this.diverted = diverted;
		this.aircrafttype = aircrafttype;
		this.ident = ident;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEstimatedarrivaltime() {
		return estimatedarrivaltime;
	}

	public void setEstimatedarrivaltime(Long estimatedarrivaltime) {
		this.estimatedarrivaltime = estimatedarrivaltime;
	}

	public Long getActualdeparturetime() {
		return actualdeparturetime;
	}

	public void setActualdeparturetime(Long actualdeparturetime) {
		this.actualdeparturetime = actualdeparturetime;
	}

	public Long getActualarrivaltime() {
		return actualarrivaltime;
	}

	public void setActualarrivaltime(Long actualarrivaltime) {
		this.actualarrivaltime = actualarrivaltime;
	}

	public Long getFiled_departuretime() {
		return filed_departuretime;
	}

	public void setFiled_departuretime(Long filed_departuretime) {
		this.filed_departuretime = filed_departuretime;
	}

	public String getOriginCity() {
		return originCity;
	}

	public void setOriginCity(String originCity) {
		this.originCity = originCity;
	}

	public String getOriginName() {
		return originName;
	}

	public void setOriginName(String originName) {
		this.originName = originName;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDiverted() {
		return diverted;
	}

	public void setDiverted(String diverted) {
		this.diverted = diverted;
	}

	public String getAircrafttype() {
		return aircrafttype;
	}

	public void setAircrafttype(String aircrafttype) {
		this.aircrafttype = aircrafttype;
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

}
