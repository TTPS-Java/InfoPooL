package DAOhiberJPA;

import interfacesDAO.ViajeDAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import objetos.Viaje;
import objetos.Viajero;

import org.springframework.stereotype.Repository;

import dataSource.MiEntityManagerFactory;
@Repository
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

	@Override
	public List<Viaje> recuperarPorConductor(String columnOrder,
			Viajero conductor) {
		return recuperarPorConductor(-1,-1,columnOrder,conductor);
	}

	@Override
	public List<Viaje> recuperarPorConductor(int page, int maxResult,
			Viajero conductor) {
		return recuperarPorConductor(page, maxResult, "", conductor);
	}

	@Override
	public List<Viaje> recuperarPorConductor(int page, int maxResult,
			String columnOrder, Viajero Conductor) {
		TypedQuery<Viaje> consulta = em.createQuery("select e from Viaje e where conductor = :cond "
				//+ "exists ( select * from Viajero v where v.id = e.id and v.id = :id)
				+ "order by id", Viaje.class);
		consulta.setParameter("cond", Conductor);
		if(page!=-1){
			consulta.setFirstResult((page-1)* maxResult)
					.setMaxResults(maxResult);
		}
		List<Viaje> list = consulta.getResultList();
		return list;
	}

}
