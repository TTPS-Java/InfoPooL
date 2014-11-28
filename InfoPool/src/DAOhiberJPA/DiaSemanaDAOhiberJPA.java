package DAOhiberJPA;

import interfacesDAO.DiaSemanaDAO;
import objetos.DiaSemana;

public class DiaSemanaDAOhiberJPA 
extends GenericDAOhiberJPA<DiaSemana> implements DiaSemanaDAO {
	public DiaSemanaDAOhiberJPA() {
		super(DiaSemana.class);
	}
}
