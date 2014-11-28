package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.DiaSemana;

public interface DiaSemanaDAO extends GenericDAO<DiaSemana>{
	public DiaSemana borrar(Serializable id);
	public void borrar(DiaSemana u);
	public DiaSemana persistir(DiaSemana u);
	public DiaSemana recuperar(Serializable id);
	public List<DiaSemana> recuperarTodos(String columnOrder);
	public List<DiaSemana> recuperarTodos(int page, int maxResult);
	public List<DiaSemana> recuperarTodos(int page, int maxResult, String columnOrder);
}
