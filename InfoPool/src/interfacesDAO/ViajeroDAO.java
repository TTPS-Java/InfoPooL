package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Viajero;

public interface ViajeroDAO extends GenericDAO<Viajero>{
	public Viajero borrar(Serializable id);
	public void borrar(Viajero u);
	public Viajero persistir(Viajero u);
	public Viajero recuperar(Serializable id);
	public List<Viajero> recuperarTodos(String columnOrder);
	public List<Viajero> recuperarTodos(int page, int maxResult);
	public List<Viajero> recuperarTodos(int page, int maxResult, String columnOrder);
}
