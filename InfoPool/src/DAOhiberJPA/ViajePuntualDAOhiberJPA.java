package DAOhiberJPA;

import org.springframework.stereotype.Repository;

import interfacesDAO.ViajePuntualDAO;
import objetos.ViajePuntual;
@Repository
public class ViajePuntualDAOhiberJPA extends GenericDAOhiberJPA<ViajePuntual> implements ViajePuntualDAO {
		public ViajePuntualDAOhiberJPA() {
			super(ViajePuntual.class);
		}

}
