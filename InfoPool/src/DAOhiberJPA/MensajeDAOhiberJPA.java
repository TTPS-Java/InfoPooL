package DAOhiberJPA;

import interfacesDAO.MensajeDAO;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import objetos.Mensaje;
import objetos.Viajero;
@Repository

public class MensajeDAOhiberJPA extends GenericDAOhiberJPA<Mensaje> implements MensajeDAO {
		public MensajeDAOhiberJPA () {
			super(Mensaje.class);
		}

		@Override
		public List<Mensaje> recuperarPorDestinatario(String columnOrder, Viajero destinatario) {
			return this.recuperarPorDestinatario(-1, -1, columnOrder, destinatario);
		}

		@Override
		public List<Mensaje> recuperarPorDestinatario(int page, int maxResult,
			Viajero destinatario) {
		return this.recuperarPorDestinatario(page, maxResult, "", destinatario);
		}
		
		@Override
		public List<Mensaje> recuperarPorDestinatario(int page, int maxResult, String columnOrder, Viajero destinatario) {
			TypedQuery<Mensaje> query = em.createQuery("select m from Mensaje m where m.para = :destinatario", Mensaje.class);
			query.setParameter("destinatario", destinatario);
			return query.getResultList();
		}
}
