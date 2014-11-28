package objetos;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class ViajePeriodico extends Viaje {
	@OneToMany
	private List<DiaSemana> dias;

	public List<DiaSemana> getDias() {
		return dias;
	}
	public void setDias(List<DiaSemana> dias) {
		this.dias = dias;
	}
	
	
}