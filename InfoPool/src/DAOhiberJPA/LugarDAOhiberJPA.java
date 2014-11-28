package DAOhiberJPA;

import objetos.Lugar;

public class LugarDAOhiberJPA 
extends GenericDAOhiberJPA<Lugar> implements LugarDAO {
	public LugarDAOhiberJPA() {
		super(Lugar.class);
	}

}
