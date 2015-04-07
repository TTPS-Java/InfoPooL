package DAOhiberJPA;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import interfacesDAO.SolicitudDAO;
import interfacesDAO.ViajeDAO;
import objetos.Solicitud;
import objetos.Viaje;
import objetos.Viajero;
@Repository
public class SolicitudDAOhiberJPA 
extends GenericDAOhiberJPA<Solicitud> implements SolicitudDAO {
	@Autowired
	private ViajeDAO viajeDAO;
	
	public SolicitudDAOhiberJPA() {
		super(Solicitud.class);
	}

	@Override
	public List<Solicitud> recuperarPorConductor(String columnOrder,
			Viajero conductor) {
		return recuperarPorConductor(-1, -1, columnOrder, conductor);
	}

	@Override
	public List<Solicitud> recuperarPorConductor(int page, int maxResult,
			Viajero conductor) {
		return recuperarPorConductor(page, maxResult, "", conductor);
	}

	@Override
	public List<Solicitud> recuperarPorConductor(int page, int maxResult,
			String columnOrder, Viajero conductor) {
		Collection<Viaje> viajes = viajeDAO.recuperarPorConductor("id", conductor);
		List<Solicitud> list = new LinkedList<Solicitud>();
		for (Viaje viaje : viajes) {
			TypedQuery<Solicitud> consulta = em.createQuery("select e from Solicitud e where viaje = :via "
					+ "order by "+columnOrder, Solicitud.class);
			consulta.setParameter("via", viaje);
			if(page!=-1){
				consulta.setFirstResult((page-1)* maxResult)
						.setMaxResults(maxResult);
			}
			list.addAll(consulta.getResultList());
		}
		return list;
	}
	
	
	
	@Override
	public List<Solicitud> recuperarPorViaje(int page, int maxResult,
			String columnOrder, Viaje viaje) {
		List<Solicitud> list = new ArrayList<Solicitud>();
			TypedQuery<Solicitud> consulta = em.createQuery("select e from Solicitud e where viaje = :via "
					+ "order by "+columnOrder, Solicitud.class);
			consulta.setParameter("via", viaje);
			if(page!=-1){
				consulta.setFirstResult((page-1)* maxResult)
						.setMaxResults(maxResult);
			}
			list.addAll(consulta.getResultList());
		
		return list;
	}
	@Override
	public List<Solicitud> recuperarPorViaje(String columnOrder,
			 Viaje viaje) {
		return recuperarPorViaje(-1, -1, columnOrder, viaje);
	}
	
	
	
	
}
