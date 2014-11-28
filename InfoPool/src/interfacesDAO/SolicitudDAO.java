package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Solicitud;

public interface SolicitudDAO extends GenericDAO<Solicitud>{
	public Solicitud borrar(Serializable id);
	public void borrar(Solicitud u);
	public Solicitud persistir(Solicitud u);
	public Solicitud recuperar(Serializable id);
	public List<Solicitud> recuperarTodos(String columnOrder);
	public List<Solicitud> recuperarTodos(int page, int maxResult);
	public List<Solicitud> recuperarTodos(int page, int maxResult, String columnOrder);
}
