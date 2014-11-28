package DAOhiberJPA;

import objetos.Mensaje;

public class MensajeDAOhiberJPA extends GenericDAOhiberJPA<Mensaje> implements MensajeDAO {
		public MensajeDAOhiberJPA () {
			super(Mensaje.class);
		}
}
