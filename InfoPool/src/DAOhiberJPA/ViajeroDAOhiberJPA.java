package DAOhiberJPA;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.ViajeroDAO;
import objetos.Viajero;
@Repository
public class ViajeroDAOhiberJPA  extends GenericDAOhiberJPA<Viajero> implements ViajeroDAO
{
	  public ViajeroDAOhiberJPA() {
		  super(Viajero.class);
	  }

	@Override
	public Viajero recuperarConViajesEstoy(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		Viajero entity = em.find(Viajero.class, id);
		entity.getViajesEstoy().size();
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	@Override
	public Viajero recuperarConViajes(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		Viajero entity = em.find(Viajero.class, id);
		entity.getViajes().size();
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	@Override
	public Viajero recuperarConCalificaciones(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		Viajero entity = em.find(Viajero.class, id);
		entity.getCalificaciones().size();
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

}
