package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Evento;

public interface EventoDAO extends GenericDAO<Evento>{
	public Evento borrar(Serializable id);
	public void borrar(Evento u);
	public Evento persistir(Evento u);
	public Evento recuperar(Serializable id);
	public List<Evento> recuperarTodos(String columnOrder);
	public List<Evento> recuperarTodos(int page, int maxResult);
	public List<Evento> recuperarTodos(int page, int maxResult, String columnOrder);
}
