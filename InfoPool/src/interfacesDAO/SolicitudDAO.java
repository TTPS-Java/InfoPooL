package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Solicitud;
import objetos.Viaje;
import objetos.Viajero;

public interface SolicitudDAO extends GenericDAO<Solicitud>{
	public Solicitud borrar(Serializable id);
	public void borrar(Solicitud u);
	public Solicitud persistir(Solicitud u);
	public Solicitud recuperar(Serializable id);
	public List<Solicitud> recuperarTodos(String columnOrder);
	public List<Solicitud> recuperarTodos(int page, int maxResult);
	public List<Solicitud> recuperarTodos(int page, int maxResult, String columnOrder);
	public List<Solicitud> recuperarPorConductor(String columnOrder, Viajero conductor);
	public List<Solicitud> recuperarPorConductor(int page, int maxResult, Viajero conductor);
	public List<Solicitud> recuperarPorConductor(int page, int maxResult, String columnOrder, Viajero conductor);
	public List<Solicitud> recuperarPorViaje(String columnOrder,Viaje viaje);
	public List<Solicitud> recuperarPorViaje(int page, int maxResult,String columnOrder, Viaje viaje);
}
