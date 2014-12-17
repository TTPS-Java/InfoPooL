package DAOhiberJPA;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;




import org.springframework.stereotype.Repository;

import dataSource.MiEntityManagerFactory;
import interfacesDAO.UsuarioDAO;
import objetos.Usuario;
@Repository
public class UsuarioDAOhiberJPA
extends GenericDAOhiberJPA<Usuario> implements UsuarioDAO {
	public UsuarioDAOhiberJPA() {
		super(Usuario.class);
	}

	@Override
	public Boolean existe(String usuario) {
		TypedQuery<Usuario> consulta = em.createQuery("select e from Usuario e where e.nombreUsuario= :nombre",Usuario.class);
		consulta.setParameter("nombre",usuario);
		boolean flag = !consulta.getResultList().isEmpty();
		return flag;
		
	}
	public Usuario recuperar(String usuario) {
		TypedQuery<Usuario> consulta = em.createQuery("select e from Usuario e where e.nombreUsuario= :nombre",Usuario.class);
		consulta.setParameter("nombre",usuario);
		List<Usuario> lis = consulta.getResultList();
		Usuario resul= null;
		if(!lis.isEmpty()){
			resul=lis.get(0);
		}
		return resul;
		
	}


}
