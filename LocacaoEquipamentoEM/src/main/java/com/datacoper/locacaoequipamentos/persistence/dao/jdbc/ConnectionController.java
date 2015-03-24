/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Java
 */
public class ConnectionController {

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

        String url = "jdbc:postgresql://localhost:5432/LocadoraDeEquipamentos";
        String usuario = "postgres";
        String senha = "admin";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Driver JDBC não encontrado!", ex);
        }

        try {
            this.connection = DriverManager.getConnection(url, usuario, senha);
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

   /* public ResultSet pesquisa(String Query) {

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema createStatement - Pesquisa", ex);
        }

        try {
            rs = stm.executeQuery(Query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema executeQuery - pesquisa", ex);
        }

        //    con.close();
        return rs;

    }

    public int insere(String Query) {

        Statement stm = null;
        //ResultSet rs = null;
        int affectedRows = 0;

        try {
            stm = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema createStatement - insere", ex);
        }

        try {
            affectedRows = stm.executeUpdate(Query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Problema executeUpdate - insere", ex);
        }

        //rs = stm.executeQuery(Query);
        //  con.close();
        return affectedRows;

    }*/
}
