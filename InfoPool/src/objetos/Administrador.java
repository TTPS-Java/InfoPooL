package objetos;

import javax.persistence.Entity;

@Entity
public class Administrador extends Usuario {
	
	public Administrador(String nombreUsuario, String contrasenia) {
		super(nombreUsuario,contrasenia);
		// TODO Auto-generated constructor stub
	}
	public Administrador(){
		
	}

	@Override
	public String toString() {
		return super.toString();
	}

	
}
