package objetos;

import java.util.List;

import javax.persistence.Entity;
@Entity
public class ViajePeriodico extends Viaje {
	private List<DiaSemana> dias;

	public List<DiaSemana> getDias() {
		return dias;
	}
	public void setDias(List<DiaSemana> dias) {
		this.dias = dias;
	}
	
	
}