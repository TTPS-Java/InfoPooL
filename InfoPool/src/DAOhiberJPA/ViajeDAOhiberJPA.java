package DAOhiberJPA;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.ViajeDAO;
import objetos.Viaje;

public class ViajeDAOhiberJPA 
extends GenericDAOhiberJPA<Viaje> implements ViajeDAO {
	public ViajeDAOhiberJPA() {
		super(Viaje.class);
	}

	@Override
	public Viaje recuperarConPasajeros(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		Viaje entity = em.find(Viaje.class, id);
		entity.getPasajeros().size();
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

}
