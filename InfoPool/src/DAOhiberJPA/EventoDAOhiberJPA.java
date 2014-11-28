package DAOhiberJPA;

import interfacesDAO.EventoDAO;
import objetos.Evento;

public class EventoDAOhiberJPA 
extends GenericDAOhiberJPA<Evento> implements EventoDAO {
	public EventoDAOhiberJPA() {
		super(Evento.class);
	}
}