package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Java
 */
public class ConnectionController {
	
	private final String BASE   = "business";
	private final String SERVER = "localhost";
	private final String PORT   = "5432";
	private final String USER   = "postgres";
	private final String PASS   = "admin";
	
	private final String URL = "jdbc:postgresql://" + SERVER + ":" + PORT + "/" + BASE;
	
    private Connection connection;

    private static ConnectionController INSTANCE;

    private ConnectionController() {
        criarConexao();
    }

    public static ConnectionController getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ConnectionController();
        }
        return INSTANCE;
    }

    private void criarConexao() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver JDBC não encontrado!", ex);
        }

        try {
            this.connection = DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Conexão com banco não efetuada!", ex);
        }

    }
    
    public Connection getConnection() {
		return this.connection;
	}
    
    public void closeConnection() {
    	try {
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao fechar conexão com o banco de dados", e);
		}
    }
}
