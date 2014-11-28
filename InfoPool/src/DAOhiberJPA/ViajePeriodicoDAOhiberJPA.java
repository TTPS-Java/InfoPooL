package DAOhiberJPA;

import interfacesDAO.ViajePeriodicoDAO;
import objetos.ViajePeriodico;

public class ViajePeriodicoDAOhiberJPA 
extends GenericDAOhiberJPA<ViajePeriodico> implements ViajePeriodicoDAO {
	public ViajePeriodicoDAOhiberJPA() {
		super(ViajePeriodico.class);
	}

}
