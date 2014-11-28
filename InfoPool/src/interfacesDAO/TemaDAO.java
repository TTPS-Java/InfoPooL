package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import clasesObjetos.Tema;

public interface TemaDAO extends GenericDAO<Tema>{
	public Tema borrar(Serializable id);
	public void borrar(Tema u);
	public Tema persistir(Tema u);
	public Tema recuperar(Serializable id);
	public List<Tema> recuperarTodos(String columnOrder);
	public List<Tema> recuperarTodos(int page, int maxResult);
	public List<Tema> recuperarTodos(int page, int maxResult, String columnOrder);
}
