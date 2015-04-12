package DAOhiberJPA;

import interfacesDAO.EventoDAO;

import java.io.Serializable;

import javax.persistence.TypedQuery;

import objetos.Evento;
import objetos.Viaje;

import org.springframework.stereotype.Repository;
@Repository

public class EventoDAOhiberJPA 
extends GenericDAOhiberJPA<Evento> implements EventoDAO {
	public EventoDAOhiberJPA() {
		super(Evento.class);
	}

	@Override
	public boolean estaEnUnViaje(Serializable idEvento) {
		TypedQuery<Viaje> consulta = em.createQuery("Select v from Viaje v where v.eventoAsociado.id = :idEvento ",Viaje.class);
		consulta.setParameter("idEvento", idEvento);
		return consulta.getResultList().size() != 0;
	}
}
