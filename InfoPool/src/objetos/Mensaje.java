package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Mensaje {
	@Id
	@GeneratedValue
	private long id;
	private String asunto;
	private String contenido;
	@OneToOne
	private Evento evento;
	@OneToOne
	private Viajero para;
	@OneToOne
	private Viajero de;
	public long getId() {
		return id;
	}
	private void setId(long id) {
		id = id;
	}
	
	public Viajero getPara() {
		return para;
	}
	public void setPara(Viajero para) {
		this.para = para;
	}
	public Viajero getDe() {
		return de;
	}
	public void setDe(Viajero de) {
		this.de = de;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
}
