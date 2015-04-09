package interfacesDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import objetos.Calificacion;

public interface CalificacionDAO extends GenericDAO<Calificacion>{
	public Calificacion borrar(Serializable id);
	public void borrar(Calificacion u);
	public Calificacion persistir(Calificacion u);
	public Calificacion recuperar(Serializable id);
	public List<Calificacion> recuperarTodos(String columnOrder);
	public List<Calificacion> recuperarTodos(int page, int maxResult);
	public List<Calificacion> recuperarTodos(int page, int maxResult, String columnOrder);
	public ArrayList<Calificacion> recuperarCalificacionesPorViaje(Serializable idViaje);
	public ArrayList<Calificacion> recuperarPorCalificado(Serializable idViajeroCalificado);
}
