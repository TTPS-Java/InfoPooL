package DAOhiberJPA;

import interfacesDAO.UsuarioDAO;
import objetos.Usuario;

public class UsuarioDAOhiberJPA
extends GenericDAOhiberJPA<Usuario> implements UsuarioDAO {
	public UsuarioDAOhiberJPA() {
		super(Usuario.class);
	}

}
