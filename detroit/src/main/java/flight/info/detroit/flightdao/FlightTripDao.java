package flight.info.detroit.flightdao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import flight.info.detroit.FlightStatus;
import flight.info.detroit.model.googlematrix.Duration;

@Repository
@Transactional
public class FlightTripDao {

	@PersistenceContext
	private EntityManager em;

	public List<FlightStatus> findAll() {

		return em.createQuery("FROM FlightStatus", FlightStatus.class).getResultList();
	}

	public void create(FlightStatus flightStatus) {

		em.persist(flightStatus);
	}

	public FlightStatus findById(Long id) {

		return em.find(FlightStatus.class, id);
	}

	public void createDuration(Duration duration) {
		em.persist(duration);
	}

	public Duration findByDuration(Long dur) {

		return em.createQuery("FROM Duration", Duration.class).getSingleResult();
	}

	public void updateFlight(FlightStatus flightStatus) {

		em.merge(flightStatus);
	}

	public void deleteFlight(Long id) {
		// Deleting with Hibernate entity manager requires fetching a reference first.
		FlightStatus fs = em.getReference(FlightStatus.class, id);
		em.remove(fs);

	}

	public void updateFlightById(Long id) {
		FlightStatus flightStatus = em.getReference(FlightStatus.class, id);
		em.persist(flightStatus);
	}

}