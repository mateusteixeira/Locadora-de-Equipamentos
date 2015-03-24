package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

public class GenericDAOJdbc {

	
	protected Connection connection;

	public GenericDAOJdbc(Connection connection) {
		this.connection = connection;
	}
	
	protected Integer getNextSequenceValue(String sequenceName) {
		String sql = "select nextval('" + sequenceName + "') as value";
		SequenceValue sequenceValue = getSingleResult(sql, SequenceValue.class);
		return sequenceValue.getValue();
	}

	protected <T> T getSingleResult(String sql, Class<T> returnType) {
		
		BeanHandler<T> resultHandler = new BeanHandler<T>(returnType);
		
		T result = executeQuery(sql, resultHandler);
		
		return result;
	}

	
	protected <T> List<T> getResult(String sql, Class<T> returnType) {
		BeanListHandler<T> resultHandler = new BeanListHandler<T>(returnType);
		
		List<T> result = executeQuery(sql, resultHandler);
		
		return result;
	}
	
	private <T> T executeQuery(String sql, ResultSetHandler<T> resultHandler) {
		QueryRunner queryRunner = new QueryRunner();
		T result;
		try {
			result = queryRunner.query(connection, sql, resultHandler);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	protected void closeResource(AutoCloseable... resources) {
		for (AutoCloseable resource : resources) {
			try {
				resource.close();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
