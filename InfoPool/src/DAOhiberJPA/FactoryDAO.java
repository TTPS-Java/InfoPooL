package DAOhiberJPA;

import interfacesDAO.AdministradorDAO;
import interfacesDAO.CalificacionDAO;
import interfacesDAO.DenunciaDAO;
import interfacesDAO.DiaSemanaDAO;
import interfacesDAO.EstadoSolicitudDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.MensajeDAO;
import interfacesDAO.SolicitudDAO;
import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajePeriodicoDAO;
import interfacesDAO.ViajePuntualDAO;
import interfacesDAO.ViajeroDAO;

public class FactoryDAO {
	public static AdministradorDAO getAdministradorDAO() {
		return new AdministradorDAOhiberJPA();
	}

	public static CalificacionDAO getCalificacionDAO() {
		return new CalificacionDAOhiberJPA();
	}

	public static DenunciaDAO getDenunciaDAO() {
		return new DenunciaDAOhiberJPA();
	}

	public static DiaSemanaDAO getDiaSemanaDAO() {
		return new DiaSemanaDAOhiberJPA();
	}

	public static EstadoSolicitudDAO getEstadoSolicitudDAO() {
		return new EstadoSolicitudDAOhiberJPA();
	}

	public static EventoDAO getEventoDAO() {
		return new EventoDAOhiberJPA();
	}

	public static LugarDAO getLugarDAO() {
		return new LugarDAOhiberJPA();
	}

	public static MensajeDAO getMensajeDAO() {
		return new MensajeDAOhiberJPA();
	}

	public static SolicitudDAO getSolicitudDAO() {
		return new SolicitudDAOhiberJPA();
	}

	public static UsuarioDAO getUsuarioDAO() {
		return new UsuarioDAOhiberJPA();
	}

	public static ViajeDAO getViajeDAO() {
		return new ViajeDAOhiberJPA();
	}

	public static ViajePeriodicoDAO getViajePeriodicoDAO() {
		return new ViajePeriodicoDAOhiberJPA();
	}

	public static ViajePuntualDAO getViajePuntualDAO() {
		return new ViajePuntualDAOhiberJPA();
	}

	public static ViajeroDAO getViajeroDAO() {
		return new ViajeroDAOhiberJPA();
	}

}
