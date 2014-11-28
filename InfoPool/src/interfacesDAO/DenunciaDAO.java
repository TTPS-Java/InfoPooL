package interfacesDAO;

import java.io.Serializable;
import java.util.List;

import objetos.Denuncia;

public interface DenunciaDAO extends GenericDAO<Denuncia>{
	public Denuncia borrar(Serializable id);
	public void borrar(Denuncia u);
	public Denuncia persistir(Denuncia u);
	public Denuncia recuperar(Serializable id);
	public List<Denuncia> recuperarTodos(String columnOrder);
	public List<Denuncia> recuperarTodos(int page, int maxResult);
	public List<Denuncia> recuperarTodos(int page, int maxResult, String columnOrder);
}
