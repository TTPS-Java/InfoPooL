package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Mensaje;
import objetos.Viajero;

public interface MensajeDAO extends GenericDAO<Mensaje>{
	public Mensaje borrar(Serializable id);
	public void borrar(Mensaje u);
	public Mensaje persistir(Mensaje u);
	public Mensaje recuperar(Serializable id);
	public List<Mensaje> recuperarTodos(String columnOrder);
	public List<Mensaje> recuperarTodos(int page, int maxResult);
	public List<Mensaje> recuperarTodos(int page, int maxResult, String columnOrder);
	public List<Mensaje> recuperarPorDestinatario(String columnOrder, Viajero destinatario);
	public List<Mensaje> recuperarPorDestinatario(int page, int maxResult, Viajero destinatario);
	public List<Mensaje> recuperarPorDestinatario(int page, int maxResult, String columnOrder, Viajero destinatario);
}
