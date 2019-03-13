package flight.info.detroit.flightdao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import flight.info.detroit.FlightStatus;

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
	
	
	
	public void update(FlightStatus flightStatus) {
		
		em.merge(flightStatus);
	}
	
}
