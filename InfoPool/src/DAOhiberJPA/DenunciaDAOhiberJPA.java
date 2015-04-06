package DAOhiberJPA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import dataSource.MiEntityManagerFactory;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import interfacesDAO.DenunciaDAO;
import objetos.Denuncia;

@Repository
public class DenunciaDAOhiberJPA extends GenericDAOhiberJPA<Denuncia> implements
		DenunciaDAO {
	public DenunciaDAOhiberJPA() {
		super(Denuncia.class);
	}

	@Override
	public Denuncia persistir(Denuncia entity) {
		return super.persistir(entity);
	}
	@Override
	public ArrayList<Denuncia> denunciasAViajero(Serializable idViajero){
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		ArrayList<Denuncia> denunciasAViajero = new ArrayList<Denuncia>();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		TypedQuery<Denuncia> consulta = em.createQuery("Select d from Denuncia d where d.denunciado.id="+idViajero,Denuncia.class);
		denunciasAViajero = (ArrayList<Denuncia>) consulta.getResultList();
		em.flush();
		etx.commit();
		em.close();
		return denunciasAViajero;
	}

}
