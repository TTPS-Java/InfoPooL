package objetos;

import java.util.Date;

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
	private Date fecha;
	@OneToOne
	private Viajero para;
	@OneToOne
	private Viajero de;
	
	public Mensaje() {
		// TODO Auto-generated constructor stub
	}
	
	public Mensaje(String asunto, String contenido, Viajero para, Viajero de) {
		super();
		this.asunto = asunto;
		this.contenido = contenido;
		this.para = para;
		this.de = de;
		this.fecha = new Date();
	}
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Mensaje [id=" + id + ", asunto=" + asunto + ", contenido="
				+ contenido + ", para=" + para + ", de="
				+ de + "]";
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
}
