package DAOhiberJPA;

import org.springframework.stereotype.Repository;

import interfacesDAO.AdministradorDAO;
import objetos.Administrador;
@Repository
public class AdministradorDAOhiberJPA extends GenericDAOhiberJPA<Administrador> implements AdministradorDAO {
	public AdministradorDAOhiberJPA() {
		super(Administrador.class);
	}

}
