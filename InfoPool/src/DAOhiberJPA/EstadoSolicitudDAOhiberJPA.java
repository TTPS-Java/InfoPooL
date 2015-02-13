package DAOhiberJPA;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import interfacesDAO.EstadoSolicitudDAO;
import objetos.EstadoSolicitud;
@Repository
public class EstadoSolicitudDAOhiberJPA 
extends GenericDAOhiberJPA<EstadoSolicitud> implements EstadoSolicitudDAO {
	public EstadoSolicitudDAOhiberJPA() {
		super(EstadoSolicitud.class);
	}
	
	public EstadoSolicitud recuperar(String estado) {
		TypedQuery<EstadoSolicitud> consulta = em.createQuery("select e from EstadoSolicitud e where e.nombre= :nombre",EstadoSolicitud.class);
		consulta.setParameter("nombre",estado);
		List<EstadoSolicitud> lis = consulta.getResultList();
		EstadoSolicitud resul= null;
		if(!lis.isEmpty()){
			resul=lis.get(0);
		}
		return resul;
		
	}

}
