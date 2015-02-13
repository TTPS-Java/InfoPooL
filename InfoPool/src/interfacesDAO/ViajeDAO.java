package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Viaje;
import objetos.Viajero;

public interface ViajeDAO extends GenericDAO<Viaje>{
	public Viaje borrar(Serializable id);
	public void borrar(Viaje u);
	public Viaje persistir(Viaje u);
	public Viaje recuperar(Serializable id);
	public List<Viaje> recuperarTodos(String columnOrder);
	public List<Viaje> recuperarTodos(int page, int maxResult);
	public List<Viaje> recuperarTodos(int page, int maxResult, String columnOrder);
	public List<Viaje> recuperarPorConductor(String columnOrder, Viajero conductor);
	public List<Viaje> recuperarPorConductor(int page, int maxResult, Viajero conductor);
	public List<Viaje> recuperarPorConductor(int page, int maxResult, String columnOrder, Viajero Conductor);
	
	public Viaje recuperarConPasajeros(Serializable id);
}
