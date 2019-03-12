package flight.info.detroit.model.googlematrix;

import java.util.List;

public class OriginAndDestination {

	private List <String> destinationAddresses;
	private List <String> originAddresses;
	
	public List<String> getDestinationAddresses() {
		return destinationAddresses;
	}
	public void setDestinationAddresses(List<String> destinationAddresses) {
		this.destinationAddresses = destinationAddresses;
	}
	public List<String> getOriginAddresses() {
		return originAddresses;
	}
	public void setOriginAddresses(List<String> originAddresses) {
		this.originAddresses = originAddresses;
	}
	
	
}
