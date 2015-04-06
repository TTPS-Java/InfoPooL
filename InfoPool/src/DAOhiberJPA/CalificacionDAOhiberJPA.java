package DAOhiberJPA;
import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.CalificacionDAO;
import objetos.Calificacion;


@Repository
public class CalificacionDAOhiberJPA
extends GenericDAOhiberJPA<Calificacion> implements CalificacionDAO {
	public CalificacionDAOhiberJPA() {
		super(Calificacion.class);
	}
	
	@Override
	public ArrayList<Calificacion> recuperarCalificacionesPorViaje(Serializable idViaje){
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		TypedQuery<Calificacion> consulta = em.createQuery("Select c from Calificacion c where c.viaje.id="+idViaje,Calificacion.class);
		ArrayList<Calificacion> calificaciones = (ArrayList<Calificacion>) consulta.getResultList();
		em.flush();
		etx.commit();
		em.close();
		return calificaciones;		
	}
	
	
	
	/*public ArrayList<CalificacionPendiente> calficacionesPendientes(Viajero viajero){
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		TypedQuery<Calificacion> consulta =em.createQuery("SELECT c from Calificacion c where c.autor.id ="+viajero.getId(),Calificacion.class);
	//Calficacioes hechas por el idUsuario
		ArrayList<Calificacion> calificacionesHechas=(ArrayList<Calificacion>) consulta.getResultList();
	//seleccionar los viajes en que participo idUsuario
		ArrayList<Viaje> viajes=(ArrayList<Viaje>) viajero.recuperarViajesEstoyFinalizados(new Date());
		ArrayList<Viaje> viajesCopia= new ArrayList<Viaje>();
		for(Viaje vf:viajes){ viajesCopia.add(vf);}
		
		for (Calificacion c:calificacionesHechas){
		 for(Viaje vc:viajesCopia){
			if(c.getAutor().getId()==viajero.getId() && c.getViaje().getId()==vc.getId()){
				viajesCopia.remove(vc);
				break;
			}
		 }}
		ArrayList<CalificacionPendiente> cp = new ArrayList<CalificacionPendiente>();
		for (Viaje v:viajesCopia){
			cp.add(new CalificacionPendiente(v.getConductor().getId(),v.getFecha(),v.getId(),v.getConductor().getNombreUsuario()));
		}
		//crear los objetos calificaciones pendientes y retornarlos
		return cp;
	}
	*/
}
