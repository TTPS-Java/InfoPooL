package DAOhiberJPA;

import interfacesDAO.ViajePuntualDAO;
import objetos.ViajePuntual;

public class ViajePuntualDAOhiberJPA extends GenericDAOhiberJPA<ViajePuntual> implements ViajePuntualDAO {
		public ViajePuntualDAOhiberJPA() {
			super(ViajePuntual.class);
		}

}
