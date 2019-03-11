package flight.info.detroit;

import java.util.List;

public class MapsResponse {

	private List <String> destination_addresses;
	private List <String> origin_addresses;
	private List <Rows> rows;
	
	public MapsResponse() {
		
	}
		
	public List<String> getDestination_addresses() {
		return destination_addresses;
	}
	public void setDestination_addresses(List<String> destination_addresses) {
		this.destination_addresses = destination_addresses;
	}
	public List<String> getOrigin_addresses() {
		return origin_addresses;
	}
	public void setOrigin_addresses(List<String> origin_addresses) {
		this.origin_addresses = origin_addresses;
	}
	public List<Rows> getRows() {
		return rows;
	}
	public void setRows(List<Rows> rows) {
		this.rows = rows;
	}
	
	
}
