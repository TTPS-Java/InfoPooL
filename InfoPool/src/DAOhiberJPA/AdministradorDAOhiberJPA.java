package DAOhiberJPA;

import interfacesDAO.AdministradorDAO;
import objetos.Administrador;

public class AdministradorDAOhiberJPA extends GenericDAOhiberJPA<Administrador> implements AdministradorDAO {
	public AdministradorDAOhiberJPA() {
		super(Administrador.class);
	}

}
