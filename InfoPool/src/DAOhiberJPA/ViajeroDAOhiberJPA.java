package DAOhiberJPA;

import interfacesDAO.ViajeroDAO;
import objetos.Viajero;

public class ViajeroDAOhiberJPA  extends GenericDAOhiberJPA<Viajero> implements ViajeroDAO
{
	  public ViajeroDAOhiberJPA() {
		  super(Viajero.class);
	  }

}
