package flight.info.detroit.model.googlematrix;

import java.util.List;

public class MapsResponse {

	private List <String> destinationAddresses;
	private List <String> originAddresses;
	private List <Rows> rows;
	
	public MapsResponse() {
		
	}
		
	public List<String> getDestinationAddresses() {
		return destinationAddresses;
	}
	public void setDestination_addresses(List<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}
	public List<String> getOriginAddresses() {
		return originAddresses;
	}
	public void setOriginAddresses(List<String> originAddresses) {
		this.originAddresses = originAddresses;
	}
	public List<Rows> getRows() {
		return rows;
	}
	public void setRows(List<Rows> rows) {
		this.rows = rows;
	}
	
	
}
