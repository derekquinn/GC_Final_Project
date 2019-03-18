package flight.info.detroit;

public class Airlines {
	
	private String iata;
	private String name;
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Airlines [iata=" + iata + ", name=" + name + "]";
	}
	

}
