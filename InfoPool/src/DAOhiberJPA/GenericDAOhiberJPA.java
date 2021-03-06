package DAOhiberJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.GenericDAO;
@Transactional
public class GenericDAOhiberJPA<T> implements GenericDAO<T> {

	@PersistenceContext
	protected EntityManager em;
	protected Class<T> persistentClass;

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public void setPersistentClass(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}

	public GenericDAOhiberJPA() {
		super();
	}

	public GenericDAOhiberJPA(Class<T> persistentClass) {
		this.persistentClass = persistentClass;
	}
	@Override
	public T actualizar(T entity) {
		em.merge(entity);
		return entity;
	}

	@Override
	public void borrar(T entity) {
		T entity2=em.merge(entity);
		em.remove(entity2);
	}

	@Override
	public T borrar(Serializable id) {
		T entity = em.find(persistentClass, id);
		em.remove(entity);
		return entity;
	}

	@Override
	public boolean existe(Serializable id) {
		boolean flag = (em.find(persistentClass, id) != null);
		return flag;
	}

	@Override
	public T persistir(T entity) {
		em.persist(entity);
		return entity;
	}

	@Override
	public T recuperar(Serializable id) {
		T entity = em.find(persistentClass, id);
		return entity;
	}

	public List<T> recuperarTodos(String columnOrder) {
		return recuperarTodos(-1, -1, columnOrder);
	}

	public List<T> recuperarTodos(int page, int maxResult) {
		return recuperarTodos(page, maxResult, "");
	}

	public List<T> recuperarTodos(int page, int maxResult,String columnOrder) {
		TypedQuery<T> consulta = em.createQuery("select e from "+persistentClass.getSimpleName()+" e order by "+columnOrder, persistentClass);
		if(page!=-1){
			consulta.setFirstResult((page-1)* maxResult)
					.setMaxResults(maxResult);
		}
		List<T> list = consulta.getResultList();
		return list;
	}

}
