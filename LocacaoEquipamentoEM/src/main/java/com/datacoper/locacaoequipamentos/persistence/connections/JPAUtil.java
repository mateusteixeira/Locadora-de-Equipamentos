package com.datacoper.locacaoequipamentos.persistence.connections;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

/**
 * Durante a execução do projeto é possível trabalhar com várias sessões com o
 * banco de dados. Na JPA a sessão com o banco é representa pelo componente
 * EntityManager.
 * 
 * <p>
 * Essa classe é responsavel por disponibilizar o(s) componente(s)
 * <code>EntityManager</code>. Utilizamos o <code>EntityManagerFactory</code>
 * fornecedor (fábrica) de sessões com o banco de dados.
 * </p>
 * 
 * <p>
 * Caso não consiga carregar a fabrica de sessões, problemas com a conexão com
 * banco ou não encontrou configurador, a execução da aplicação é interrompida
 * (lança <code>ExceptionInInitializerError</code>).
 * </p>
 * 
 * @see persistence.xml
 * 
 * @author YaW Tecnologia
 */
public abstract class JPAUtil {

	private static Logger log = Logger.getLogger(JPAUtil.class);

	/** Unidade de persistencia definida no arquivo <code>persistence.xml</code> */
	private static final String PERSISTENCE_UNIT_NAME = "locacaoPU";

	private static EntityManagerFactory emf;

	private static EntityManager em;

	static {
		try {
			emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		} catch (Throwable ex) {
			log.error("Não conseguiu carregar a EntityManagerFactory: " + ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static EntityManager createEntityManager() {
		if (em == null) {
			if (!emf.isOpen())
				throw new RuntimeException("EntityManagerFactory está fechada!");
			em = emf.createEntityManager();
		}
		return em;
	}

	public static void closeEntityManagerFactory() {
		if (emf.isOpen()) {
			emf.close();
		}
	}

}
