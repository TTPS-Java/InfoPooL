package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Lugar;

public interface LugarDAO extends GenericDAO<Lugar>{
	public Lugar borrar(Serializable id);
	public void borrar(Lugar u);
	public Lugar persistir(Lugar u);
	public Lugar recuperar(Serializable id);
	public List<Lugar> recuperarTodos(String columnOrder);
	public List<Lugar> recuperarTodos(int page, int maxResult);
	public List<Lugar> recuperarTodos(int page, int maxResult, String columnOrder);
}
