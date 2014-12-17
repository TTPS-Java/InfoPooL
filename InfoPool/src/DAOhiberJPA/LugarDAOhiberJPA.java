package DAOhiberJPA;

import org.springframework.stereotype.Repository;

import interfacesDAO.LugarDAO;
import objetos.Lugar;
@Repository
public class LugarDAOhiberJPA 
extends GenericDAOhiberJPA<Lugar> implements LugarDAO {
	public LugarDAOhiberJPA() {
		super(Lugar.class);
	}

}
