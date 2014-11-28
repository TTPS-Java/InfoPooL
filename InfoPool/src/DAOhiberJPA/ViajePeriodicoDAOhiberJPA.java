package DAOhiberJPA;

import objetos.ViajePeriodico;

public class ViajePeriodicoDAOhiberJPA 
extends GenericDAOhiberJPA<ViajePeriodico> implements ViajePeriodicoDAO {
	public ViajePeriodicoDAOhiberJPA() {
		super(ViajePeriodico.class);
	}

}
