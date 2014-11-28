package DAOhiberJPA;

import interfacesDAO.DenunciaDAO;
import objetos.Denuncia;

public class DenunciaDAOhiberJPA  extends GenericDAOhiberJPA<Denuncia> implements DenunciaDAO
{
	  public DenunciaDAOhiberJPA() {
		  super(Denuncia.class);
	  }

}