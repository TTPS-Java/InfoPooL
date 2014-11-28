package DAOhiberJPA;

public class FactoryDAO {
	public static AdministradorDAO getAdministradorDAO() {
		return new AdministradorDAOhiberJPA();
	}
}
