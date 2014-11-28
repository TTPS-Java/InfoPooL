package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	public Usuario borrar(Serializable id);
	public void borrar(Usuario u);
	public Usuario persistir(Usuario u);
	public Usuario recuperar(Serializable id);
	public List<Usuario> recuperarTodos(String columnOrder);
	public List<Usuario> recuperarTodos(int page, int maxResult);
	public List<Usuario> recuperarTodos(int page, int maxResult, String columnOrder);
}
