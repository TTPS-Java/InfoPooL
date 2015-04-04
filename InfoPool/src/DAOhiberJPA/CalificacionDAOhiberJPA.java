package DAOhiberJPA;
import org.springframework.stereotype.Repository;

import interfacesDAO.CalificacionDAO;
import objetos.Calificacion;


@Repository
public class CalificacionDAOhiberJPA
extends GenericDAOhiberJPA<Calificacion> implements CalificacionDAO {
	public CalificacionDAOhiberJPA() {
		super(Calificacion.class);
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
