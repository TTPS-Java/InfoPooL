package DAOhiberJPA;

import org.springframework.stereotype.Repository;

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

}
