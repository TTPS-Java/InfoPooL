package DAOhiberJPA;

import org.springframework.stereotype.Repository;

import interfacesDAO.EventoDAO;
import objetos.Evento;
@Repository

public class EventoDAOhiberJPA 
extends GenericDAOhiberJPA<Evento> implements EventoDAO {
	public EventoDAOhiberJPA() {
		super(Evento.class);
	}
}
