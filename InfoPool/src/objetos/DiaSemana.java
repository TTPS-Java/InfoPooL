package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DiaSemana {
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	
	public DiaSemana() {
		// TODO Auto-generated constructor stub
	}
	
	public DiaSemana(String nombre) {
		super();
		this.nombre = nombre;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "DiaSemana [id=" + id + ", nombre=" + nombre + "]";
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (object == null)
			return false;

		final DiaSemana b = (DiaSemana) object;

		if (this.getId() != 0 && b.getId() != 0) {
			return this.getId() == b.getId();
		}
		return false;
	}
	
	
}
