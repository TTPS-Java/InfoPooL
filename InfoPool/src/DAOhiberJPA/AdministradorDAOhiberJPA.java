package DAOhiberJPA;

import objetos.Administrador;

public class AdministradorDAOhiberJPA extends GenericDAOhiberJPA<Administrador> implements AdministradorDAO {
	public AdministradorDAOhiberJPA() {
		super(Administrador.class);
	}

}
