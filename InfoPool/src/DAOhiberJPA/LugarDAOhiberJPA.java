package DAOhiberJPA;

import interfacesDAO.LugarDAO;
import objetos.Lugar;

public class LugarDAOhiberJPA 
extends GenericDAOhiberJPA<Lugar> implements LugarDAO {
	public LugarDAOhiberJPA() {
		super(Lugar.class);
	}

}
