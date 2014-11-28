package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.ViajePeriodico;

public interface ViajePeriodicoDAO extends GenericDAO<ViajePeriodico>{
	public ViajePeriodico borrar(Serializable id);
	public void borrar(ViajePeriodico u);
	public ViajePeriodico persistir(ViajePeriodico u);
	public ViajePeriodico recuperar(Serializable id);
	public List<ViajePeriodico> recuperarTodos(String columnOrder);
	public List<ViajePeriodico> recuperarTodos(int page, int maxResult);
	public List<ViajePeriodico> recuperarTodos(int page, int maxResult, String columnOrder);
}
