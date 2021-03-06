package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
@Entity @Inheritance(strategy=InheritanceType.JOINED)
public class Usuario {
	@Id
	@GeneratedValue
	private long id;
	private String nombreUsuario;
	private String contrasenia;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}
	
	public Usuario(String nombreUsuario, String contrasenia) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.contrasenia = contrasenia;
	}
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombreUsuario=" + nombreUsuario
				+ ", contrasenia=" + contrasenia + "]";
	}
	
	
}
