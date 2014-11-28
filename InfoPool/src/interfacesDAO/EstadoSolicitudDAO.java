package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.EstadoSolicitud;

public interface EstadoSolicitudDAO extends GenericDAO<EstadoSolicitud>{
	public EstadoSolicitud borrar(Serializable id);
	public void borrar(EstadoSolicitud u);
	public EstadoSolicitud persistir(EstadoSolicitud u);
	public EstadoSolicitud recuperar(Serializable id);
	public List<EstadoSolicitud> recuperarTodos(String columnOrder);
	public List<EstadoSolicitud> recuperarTodos(int page, int maxResult);
	public List<EstadoSolicitud> recuperarTodos(int page, int maxResult, String columnOrder);
}
