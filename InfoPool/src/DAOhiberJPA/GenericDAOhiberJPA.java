package DAOhiberJPA;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.GenericDAO;

public class GenericDAOhiberJPA<T> implements GenericDAO<T> {

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
		EntityManager em = MiEntityManagerFactory.getEMF()
				.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.merge(entity);
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	@Override
	public void borrar(T entity) {
		EntityManager em = MiEntityManagerFactory.getEMF()
				.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.remove(entity);
		em.flush();
		etx.commit();
		em.close();
	}

	@Override
	public T borrar(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF()
				.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		T entity = em.find(persistentClass, id);
		em.remove(entity);
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	@Override
	public boolean existe(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF()
				.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		boolean flag = (em.find(persistentClass, id) != null);
		em.flush();
		etx.commit();
		em.close();
		return flag;
	}

	@Override
	public T persistir(T entity) {
		EntityManager em = MiEntityManagerFactory.getEMF()
				.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		em.persist(entity);
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	@Override
	public T recuperar(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF()
				.createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		T entity = em.find(persistentClass, id);
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	public List<T> recuperarTodos(String columnOrder) {
		return recuperarTodos(-1, -1, columnOrder);
	}

	public List<T> recuperarTodos(int page, int maxResult) {
		return recuperarTodos(page, maxResult, "");
	}

	public List<T> recuperarTodos(int page, int maxResult,String columnOrder) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		TypedQuery<T> consulta = em.createQuery("select e from "+persistentClass.getSimpleName()+" e order by "+columnOrder, persistentClass);
		if(page!=-1){
			consulta.setFirstResult((page-1)* maxResult)
					.setMaxResults(maxResult);
		}
		List<T> list = consulta.getResultList();
		em.flush();
		etx.commit();
		em.close();
		return list;
	}

}
