package DAOhiberJPA;

import objetos.Usuario;

public class UsuarioDAOhiberJPA
extends GenericDAOhiberJPA<Usuario> implements UsuarioDAO {
	public UsuarioDAOhiberJPA() {
		super(Usuario.class);
	}

}
