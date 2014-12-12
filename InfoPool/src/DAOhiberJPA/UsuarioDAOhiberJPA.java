package DAOhiberJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;



import dataSource.MiEntityManagerFactory;
import interfacesDAO.UsuarioDAO;
import objetos.Usuario;

public class UsuarioDAOhiberJPA
extends GenericDAOhiberJPA<Usuario> implements UsuarioDAO {
	public UsuarioDAOhiberJPA() {
		super(Usuario.class);
	}

	@Override
	public Boolean existe(String usuario) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		TypedQuery<Usuario> consulta = em.createQuery("select e from Usuario e where e.nombreUsuario= :nombre",Usuario.class);
		consulta.setParameter("nombre",usuario);
		boolean flag = !consulta.getResultList().isEmpty();
		em.flush();
		etx.commit();
		em.close();
		return flag;
		
	}
	public Usuario recuperar(String usuario) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		TypedQuery<Usuario> consulta = em.createQuery("select e from Usuario e where e.nombreUsuario= :nombre",Usuario.class);
		consulta.setParameter("nombre",usuario);
		List<Usuario> lis = consulta.getResultList();
		Usuario resul= null;
		if(!lis.isEmpty()){
			resul=lis.get(0);
		}
		em.flush();
		etx.commit();
		em.close();
		return resul;
		
	}


}
