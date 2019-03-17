package flight.info.detroit;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

@Embeddable
public class OperationalTimes {
	@Column(name="pub_departure")
	private AircraftTiming publishedDeparture;
	@Transient
	private AircraftTiming publishedArrival;
	@Transient
	private AircraftTiming scheduledGateDeparture;
	@Transient
	private AircraftTiming estimatedGateDeparture;
	@Transient
	private AircraftTiming actualGateDeparture;
	@Transient
	private AircraftTiming flightPlanPlannedDeparture;
	@Transient
	private AircraftTiming estimatedRunwayDeparture;
	@Transient
	private AircraftTiming actualRunwayDeparture;
	@Transient
	private AircraftTiming scheduledGateArrival;
	@Transient
	private AircraftTiming estimatedGateArrival;
	@Transient
	private AircraftTiming actualGateArrival;
	@Transient
	private AircraftTiming flightPlanPlannedArrival;
	@Transient
	private AircraftTiming estimatedRunwayArrival;
	@Transient
	private AircraftTiming actualRunwayArrival;

	public OperationalTimes() {

	}

	public OperationalTimes(AircraftTiming publishedDeparture, AircraftTiming publishedArrival,
			AircraftTiming scheduledGateDeparture, AircraftTiming estimatedGateDeparture,
			AircraftTiming actualGateDeparture, AircraftTiming flightPlanPlannedDeparture,
			AircraftTiming estimatedRunwayDeparture, AircraftTiming actualRunwayDeparture,
			AircraftTiming scheduledGateArrival, AircraftTiming estimatedGateArrival, AircraftTiming actualGateArrival,
			AircraftTiming flightPlanPlannedArrival, AircraftTiming estimatedRunwayArrival,
			AircraftTiming actualRunwayArrival) {
		super();
		this.publishedDeparture = publishedDeparture;
		this.publishedArrival = publishedArrival;
		this.scheduledGateDeparture = scheduledGateDeparture;
		this.estimatedGateDeparture = estimatedGateDeparture;
		this.actualGateDeparture = actualGateDeparture;
		this.flightPlanPlannedDeparture = flightPlanPlannedDeparture;
		this.estimatedRunwayDeparture = estimatedRunwayDeparture;
		this.actualRunwayDeparture = actualRunwayDeparture;
		this.scheduledGateArrival = scheduledGateArrival;
		this.estimatedGateArrival = estimatedGateArrival;
		this.actualGateArrival = actualGateArrival;
		this.flightPlanPlannedArrival = flightPlanPlannedArrival;
		this.estimatedRunwayArrival = estimatedRunwayArrival;
		this.actualRunwayArrival = actualRunwayArrival;
	}

	public AircraftTiming getPublishedDeparture() {
		return publishedDeparture;
	}

	public void setPublishedDeparture(AircraftTiming publishedDeparture) {
		this.publishedDeparture = publishedDeparture;
	}

	public AircraftTiming getPublishedArrival() {
		return publishedArrival;
	}

	public void setPublishedArrival(AircraftTiming publishedArrival) {
		this.publishedArrival = publishedArrival;
	}

	public AircraftTiming getScheduledGateDeparture() {
		return scheduledGateDeparture;
	}

	public void setScheduledGateDeparture(AircraftTiming scheduledGateDeparture) {
		this.scheduledGateDeparture = scheduledGateDeparture;
	}

	public AircraftTiming getEstimatedGateDeparture() {
		return estimatedGateDeparture;
	}

	public void setEstimatedGateDeparture(AircraftTiming estimatedGateDeparture) {
		this.estimatedGateDeparture = estimatedGateDeparture;
	}

	public AircraftTiming getActualGateDeparture() {
		return actualGateDeparture;
	}

	public void setActualGateDeparture(AircraftTiming actualGateDeparture) {
		this.actualGateDeparture = actualGateDeparture;
	}

	public AircraftTiming getFlightPlanPlannedDeparture() {
		return flightPlanPlannedDeparture;
	}

	public void setFlightPlanPlannedDeparture(AircraftTiming flightPlanPlannedDeparture) {
		this.flightPlanPlannedDeparture = flightPlanPlannedDeparture;
	}

	public AircraftTiming getEstimatedRunwayDeparture() {
		return estimatedRunwayDeparture;
	}

	public void setEstimatedRunwayDeparture(AircraftTiming estimatedRunwayDeparture) {
		this.estimatedRunwayDeparture = estimatedRunwayDeparture;
	}

	public AircraftTiming getActualRunwayDeparture() {
		return actualRunwayDeparture;
	}

	public void setActualRunwayDeparture(AircraftTiming actualRunwayDeparture) {
		this.actualRunwayDeparture = actualRunwayDeparture;
	}

	public AircraftTiming getScheduledGateArrival() {
		return scheduledGateArrival;
	}

	public void setScheduledGateArrival(AircraftTiming scheduledGateArrival) {
		this.scheduledGateArrival = scheduledGateArrival;
	}

	public AircraftTiming getEstimatedGateArrival() {
		return estimatedGateArrival;
	}

	public void setEstimatedGateArrival(AircraftTiming estimatedGateArrival) {
		this.estimatedGateArrival = estimatedGateArrival;
	}

	public AircraftTiming getActualGateArrival() {
		return actualGateArrival;
	}

	public void setActualGateArrival(AircraftTiming actualGateArrival) {
		this.actualGateArrival = actualGateArrival;
	}

	public AircraftTiming getFlightPlanPlannedArrival() {
		return flightPlanPlannedArrival;
	}

	public void setFlightPlanPlannedArrival(AircraftTiming flightPlanPlannedArrival) {
		this.flightPlanPlannedArrival = flightPlanPlannedArrival;
	}

	public AircraftTiming getEstimatedRunwayArrival() {
		return estimatedRunwayArrival;
	}

	public void setEstimatedRunwayArrival(AircraftTiming estimatedRunwayArrival) {
		this.estimatedRunwayArrival = estimatedRunwayArrival;
	}

	public AircraftTiming getActualRunwayArrival() {
		return actualRunwayArrival;
	}

	public void setActualRunwayArrival(AircraftTiming actualRunwayArrival) {
		this.actualRunwayArrival = actualRunwayArrival;
	}

	@Override
	public String toString() {
		return "OperationalTimes [publishedDeparture=" + publishedDeparture + ", publishedArrival=" + publishedArrival
				+ ", scheduledGateDeparture=" + scheduledGateDeparture + ", estimatedGateDeparture="
				+ estimatedGateDeparture + ", actualGateDeparture=" + actualGateDeparture
				+ ", flightPlanPlannedDeparture=" + flightPlanPlannedDeparture + ", estimatedRunwayDeparture="
				+ estimatedRunwayDeparture + ", actualRunwayDeparture=" + actualRunwayDeparture
				+ ", scheduledGateArrival=" + scheduledGateArrival + ", estimatedGateArrival=" + estimatedGateArrival
				+ ", actualGateArrival=" + actualGateArrival + ", flightPlanPlannedArrival=" + flightPlanPlannedArrival
				+ ", estimatedRunwayArrival=" + estimatedRunwayArrival + ", actualRunwayArrival=" + actualRunwayArrival
				+ "]";
	}

}
