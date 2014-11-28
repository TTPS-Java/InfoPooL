package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.ViajePuntual;

public interface ViajePuntualDAO extends GenericDAO<ViajePuntual>{
	public ViajePuntual borrar(Serializable id);
	public void borrar(ViajePuntual u);
	public ViajePuntual persistir(ViajePuntual u);
	public ViajePuntual recuperar(Serializable id);
	public List<ViajePuntual> recuperarTodos(String columnOrder);
	public List<ViajePuntual> recuperarTodos(int page, int maxResult);
	public List<ViajePuntual> recuperarTodos(int page, int maxResult, String columnOrder);
}
