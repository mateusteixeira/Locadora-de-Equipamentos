package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.beans.Transient;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PersistenceException;
import javax.persistence.Table;

import com.datacoper.locacaoequipamentos.common.util.ReflectionUtils;
import com.datacoper.locacaoequipamentos.persistence.connections.ConnectionController;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;

public abstract class AbstractDAOJdbc<T extends Object, PK extends Object> implements DAO<T, PK> {

	protected Connection connection;

	public AbstractDAOJdbc() {
		this(ConnectionController.getInstance().getConnection());
	}

	public AbstractDAOJdbc(Connection connection) {
		this.connection = connection;
	}

	@Override
	public T save(T object) throws Exception {
		if (getId(object) == null) {
			return insert(object);
		} else {
			return update(object);
		}
	}

	private Object getId(T object) throws IllegalArgumentException, IllegalAccessException {
		Object o = ReflectionUtils.getValueByFieldByAnnotation(object.getClass(), Id.class, object);
		if (o == null) {
			o = ReflectionUtils.getValueByFieldByAnnotation(object.getClass(), EmbeddedId.class, object);
		}
		return o;
	}

	public T update(T object) throws IllegalArgumentException, IllegalAccessException {
		Object o = getId(object);
		Map<String, Object> map = new HashMap<>();
		Class classe = object.getClass();

		try {

			for (Field field : classe.getDeclaredFields()) {
				if (!field.isAnnotationPresent(Transient.class)) {
					if (field.isAnnotationPresent(Column.class) && !field.isAnnotationPresent(Id.class) && !field.isAnnotationPresent(EmbeddedId.class)) {
						map.put(getColumnName(field), ReflectionUtils.getValue(field, object));
					}
				}
			}

			String sqlUpdate = "UPDATE " + getTableName(object) + " SET # " + getWhere(object);

			String valores = "";

			Set<Entry<String, Object>> entrySet = map.entrySet();

			for (Entry<String, Object> m : entrySet) {
				valores += m.getKey() + "=" + getFormattedValue(m.getValue()) + ",";
			}

			valores = valores.substring(0, valores.length() - 1);

			sqlUpdate = sqlUpdate.replaceAll("#", valores);

			System.out.println(sqlUpdate);
			Statement st = connection.createStatement();
			st.executeUpdate(sqlUpdate);

		} catch (IllegalArgumentException e) {
			throw new PersistenceException(e);
		} catch (IllegalAccessException e) {
			throw new PersistenceException(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public T insert(T object) {
		Map<String, Object> map = new HashMap<>();
		Class classe = object.getClass();

		try {

			for (Field field : classe.getDeclaredFields()) {
				if (!field.isAnnotationPresent(Transient.class)) {
					if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
						map.put(getColumnName(field), ReflectionUtils.getValue(field, object));
					}
				}
			}

			String sqlInsert = "INSERT INTO " + getTableName(object) + "(#) VALUES (&)";
			String colunas = "";
			String valores = "";

			Set<Entry<String, Object>> entrySet = map.entrySet();

			for (Entry<String, Object> m : entrySet) {
				colunas += m.getKey() + ",";
				valores += getFormattedValue(m.getValue()) + ",";
			}

			colunas = colunas.substring(0, colunas.length() - 1);
			valores = valores.substring(0, valores.length() - 1);

			sqlInsert = sqlInsert.replaceAll("#", colunas).replaceAll("&", valores);
			System.out.println(sqlInsert);
			Statement st = connection.createStatement();
			st.executeUpdate(sqlInsert);

		} catch (IllegalArgumentException e) {
			throw new PersistenceException(e);
		} catch (IllegalAccessException e) {
			throw new PersistenceException(e);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public String getFormattedValue(Object o) {
		String retorno = "";

		if (o instanceof String) {
			retorno = "'" + o.toString() + "'";
		}else if (o instanceof Date) {
			retorno = "'" + new SimpleDateFormat("yyyy-MM-dd").format((Date) o) + "'";
		}else if(o instanceof Enum){
			Object[] e = ((Class<? extends Object>)o.getClass()).getEnumConstants();
			for (int i = 0; i < e.length; i++) {
				if (o.equals(e[i])) { 
					retorno = String.valueOf(i);
				}
			}
		} else if (o.getClass().isAnnotationPresent(Entity.class)) {
			
		}
		else {
			retorno = String.valueOf(o);
		}

		return retorno;
	}

	@Override
	public void remove(T object) throws Exception {

		String sql = "DELETE FROM " + getTableName(object) + getWhere(object);

		PreparedStatement pst = connection.prepareStatement(sql);
		pst.executeUpdate();
	}

	protected String getWhere(T object) {
		String where = "";

		Set<Entry<String, Object>> entrySet = getKey(object).entrySet();
		for (Entry<String, Object> m : entrySet) {
			where = m.getKey() + " = " + m.getValue().toString() + " AND ";
		}

		if (!where.isEmpty()) {
			where = " WHERE " + where.substring(0, where.length() - 4);
		}

		return where;
	}

	private Map<String, Object> getKey(T object) {
		Map<String, Object> map = new HashMap<>();
		Class classe = object.getClass();
		Object valor = null;
		boolean isEmbeddeId = false;
		try {
			for (Field field : classe.getDeclaredFields()) {
				if (field.isAnnotationPresent(Id.class)) {
					map.put(getColumnName(field), ReflectionUtils.getValue(field, object));
					break;
				} else if (field.isAnnotationPresent(EmbeddedId.class)) {
					valor = ReflectionUtils.getValue(field, object);
					isEmbeddeId = true;
					break;
				}
			}

			if (isEmbeddeId) {
				for (Field field : classe.getDeclaredFields()) {
					map.put(getColumnName(field), ReflectionUtils.getValue(field, object));
				}
			}
		} catch (Exception e) {
			throw new PersistenceException("Problema ao obter campos de entidade - AbstractDAOJdbc." + e);
		}
		return map;
	}

	private String getTableName(T object) {
		String nomeTabela = null;
		Class classe = object.getClass();
		if (classe.isAnnotationPresent(Table.class)) {
			nomeTabela = ((Table) classe.getAnnotation(Table.class)).name();
		}
		if (nomeTabela == null || nomeTabela.isEmpty()) {
			nomeTabela = classe.getSimpleName();
		}

		return nomeTabela;

	}

	private String getColumnName(Field field) {
		String coluna = null;
		if (field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
			coluna = ((Column) field.getAnnotation(Column.class)).name();
			if (coluna == null || coluna.isEmpty()) {
				coluna = field.getName();
			}
		}

		return coluna;
	}

	@Override
	public T findById(PK id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer nextId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	//
	//
	// public AbstractDAOJdbc(Connection connection) {
	// this.connection = connection;
	// }
	//
	// protected Integer getNextSequenceValue(String sequenceName) {
	// String sql = "select nextval('" + sequenceName + "') as value";
	// SequenceValue sequenceValue = getSingleResult(sql, SequenceValue.class);
	// return sequenceValue.getValue();
	// }
	//
	// protected <T> T getSingleResult(String sql, Class<T> returnType) {
	//
	// MyBeanHandler<T> resultHandler = new MyBeanHandler<T>(returnType);
	//
	// T result = executeQuery(sql, resultHandler);
	//
	// return result;
	// }
	//
	//
	// protected <T> List<T> getResult(String sql, Class<T> returnType) {
	// MyBeanListHandler<T> resultHandler = new
	// MyBeanListHandler<T>(returnType);
	//
	// List<T> result = executeQuery(sql, resultHandler);
	//
	// return result;
	// }
	//
	// private <T> T executeQuery(String sql, ResultSetHandler<T> resultHandler)
	// {
	// System.out.println(sql);
	// QueryRunner queryRunner = new QueryRunner();
	// T result;
	// try {
	// result = queryRunner.query(connection, sql, resultHandler);
	// } catch (SQLException e) {
	// throw new RuntimeException(e);
	// }
	// return result;
	// }
	//
	// protected void closeResource(AutoCloseable... resources) {
	// for (AutoCloseable resource : resources) {
	// try {
	// resource.close();
	// } catch (Exception e) {
	// throw new RuntimeException(e);
	// }
	// }
	// }

}
