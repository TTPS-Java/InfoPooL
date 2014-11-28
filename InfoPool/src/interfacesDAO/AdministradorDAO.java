package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Administrador;

public interface AdministradorDAO extends GenericDAO<Administrador>{
	public Administrador borrar(Serializable id);
	public void borrar(Administrador u);
	public Administrador persistir(Administrador u);
	public Administrador recuperar(Serializable id);
	public List<Administrador> recuperarTodos(String columnOrder);
	public List<Administrador> recuperarTodos(int page, int maxResult);
	public List<Administrador> recuperarTodos(int page, int maxResult, String columnOrder);
}
