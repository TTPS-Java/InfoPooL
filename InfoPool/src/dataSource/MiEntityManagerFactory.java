package dataSource;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class MiEntityManagerFactory {
	private static final EntityManagerFactory em = Persistence
			.createEntityManagerFactory("trabajojava");

	public static EntityManagerFactory getEMF() {
		return em;
	}
}
