package DAOhiberJPA;

import interfacesDAO.ViajeDAO;
import objetos.Viaje;

public class ViajeDAOhiberJPA 
extends GenericDAOhiberJPA<Viaje> implements ViajeDAO {
	public ViajeDAOhiberJPA() {
		super(Viaje.class);
	}

}
