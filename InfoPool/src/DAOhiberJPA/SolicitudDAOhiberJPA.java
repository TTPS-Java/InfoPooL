package DAOhiberJPA;

import objetos.Solicitud;

public class SolicitudDAOhiberJPA 
extends GenericDAOhiberJPA<Solicitud> implements SolicitudDAO {
	public SolicitudDAOhiberJPA() {
		super(Solicitud.class);
	}
}
