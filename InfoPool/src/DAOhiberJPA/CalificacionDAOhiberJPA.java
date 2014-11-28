package DAOhiberJPA;

import interfacesDAO.CalificacionDAO;
import objetos.Calificacion;

public class CalificacionDAOhiberJPA
extends GenericDAOhiberJPA<Calificacion> implements CalificacionDAO {
	public CalificacionDAOhiberJPA() {
		super(Calificacion.class);
	}
}
