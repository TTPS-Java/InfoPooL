package DAOhiberJPA;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.stereotype.Repository;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.ViajePeriodicoDAO;
import objetos.ViajePeriodico;
@Repository
public class ViajePeriodicoDAOhiberJPA 
extends GenericDAOhiberJPA<ViajePeriodico> implements ViajePeriodicoDAO {
	public ViajePeriodicoDAOhiberJPA() {
		super(ViajePeriodico.class);
	}

	@Override
	public ViajePeriodico recuperarConDias(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		ViajePeriodico entity = em.find(ViajePeriodico.class, id);
	if(entity!=null){
		entity.getDias().size();
	}
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

}
