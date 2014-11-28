package DAOhiberJPA;

import objetos.EstadoSolicitud;

public class EstadoSolicitudDAOhiberJPA 
extends GenericDAOhiberJPA<EstadoSolicitud> implements EstadoSolicitudDAO {
	public EstadoSolicitudDAOhiberJPA() {
		super(EstadoSolicitud.class);
	}

}
