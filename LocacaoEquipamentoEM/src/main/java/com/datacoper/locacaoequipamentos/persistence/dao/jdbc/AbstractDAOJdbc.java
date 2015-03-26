package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.beans.Transient;
import java.lang.annotation.Annotation;
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
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.PersistenceException;
import javax.persistence.Table;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.eclipse.persistence.internal.sessions.remote.SequencingFunctionCall.GetNextValue;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;
import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.model.enums.Comparador;
import com.datacoper.locacaoequipamentos.common.util.ReflectionUtils;
import com.datacoper.locacaoequipamentos.persistence.connections.ConnectionController;
import com.datacoper.locacaoequipamentos.persistence.dao.DAO;
import com.datacoper.locacaoequipamentos.persistence.dbutil.MyBeanHandler;
import com.datacoper.locacaoequipamentos.persistence.dbutil.MyBeanListHandler;
import com.datacoper.locacaoequipamentos.persistence.util.SequenceValue;

public abstract class AbstractDAOJdbc<T extends Object, PK extends Object> implements DAO<T, PK> {

	protected Connection connection;
	private Class<T> entityClass;
	
	public AbstractDAOJdbc(Class<T> entityClass) {
		this(ConnectionController.getInstance().getConnection(), entityClass);
	}

	public AbstractDAOJdbc(Connection connection, Class<T> entityClass) {
		this.connection = connection;
		this.entityClass = entityClass;
	}

	@Override
	public T save(T object) throws Exception {
		if (getId(object) == null) {
			return insert(object);
		} else {
			return update(object);
		}
	}

	private Object getId(Object object) throws IllegalArgumentException, IllegalAccessException {
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
					if (field.isAnnotationPresent(Enumerated.class) || field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(JoinColumn.class)
							&& !field.isAnnotationPresent(Id.class) && !field.isAnnotationPresent(EmbeddedId.class)) {
						map.put(getColumnName(field), ReflectionUtils.getValue(field, object));
					}
				}
			}

			String sqlUpdate = "UPDATE " + getTableName(object.getClass()) + " SET # " + getWhere(object);

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
			throw new PersistenceException(e);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return null;
	}

	public T insert(T object) {
		Map<String, Object> map = new HashMap<>();
		Class classe = object.getClass();

		try {

			for (Field field : classe.getDeclaredFields()) {
				if (!field.isAnnotationPresent(Transient.class)) {
					if (field.isAnnotationPresent(Enumerated.class) || field.isAnnotationPresent(Column.class) || field.isAnnotationPresent(JoinColumn.class)
							|| field.isAnnotationPresent(Id.class) || field.isAnnotationPresent(EmbeddedId.class)) {
						Object value = ReflectionUtils.getValue(field, object);
						if (value == null && field.isAnnotationPresent(GeneratedValue.class)) {
							value = nextId(field);
						}
						
						map.put(getColumnName(field), value);
					}
				}
			}

			String sqlInsert = "INSERT INTO " + getTableName(object.getClass()) + "(#) VALUES (&)";
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
			throw new PersistenceException(e);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}

		return null;
	}

	public List<T> search(String filtro, String valorFiltro, Class<T> classe) throws Exception {
		String col = "";
		String where = "";
		for (Field f : classe.getDeclaredFields()) {
			if (f.isAnnotationPresent(ColumnTableSearch.class)) {
				ColumnTableSearch annotation = f.getAnnotation(ColumnTableSearch.class);
				col += getColumnName(f) + ",";

				if (filtro.equals(f.getName())) {
					where += getColumnName(f) + getFormatterValue(annotation.comparador(), valorFiltro);
				}
			}
		}
		where = where.equals("") ? where : " WHERE ";

		String sql = "SELECT " + col.substring(0, col.length() - 1) + " from " + getTableName(classe) + " #";
		sql = sql.replaceAll("#", where);

		return getResult(sql, classe);
	}

	private String getFormatterValue(Comparador cc, String valor) throws Exception {
		if (cc.equals(Comparador.LIKE)) {
			return " "+ cc.getSql() + " '%" + valor + "%'";
		} else {
			return cc.getSql() + getFormattedValue(valor);
		}
	}

	public String getFormattedValue(Object o) throws Exception {
		String retorno = "";

		if (o instanceof String) {
			retorno = "'" + o.toString() + "'";
		} else if (o instanceof Date) {
			retorno = "'" + new SimpleDateFormat("yyyy-MM-dd").format((Date) o) + "'";
		} else if (o instanceof Enum) {
			Object[] e = ((Class<? extends Object>) o.getClass()).getEnumConstants();
			for (int i = 0; i < e.length; i++) {
				if (o.equals(e[i])) {
					retorno = String.valueOf(i);
				}
			}
		} else if (o.getClass().isAnnotationPresent(Entity.class)) {
			for (Field f : o.getClass().getDeclaredFields()) {
				if (f.isAnnotationPresent(Id.class)) {
					retorno = getFormattedValue(getId(o));
				}
			}
		} else {
			retorno = String.valueOf(o);
		}

		return retorno;
	}

	@Override
	public void remove(T object) throws Exception {

		String sql = "DELETE FROM " + getTableName(object.getClass()) + getWhere(object);

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

	private String getTableName(Class<?> classe) {
		String nomeTabela = null;
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
		if (field.isAnnotationPresent(Column.class)) {
			coluna = ((Column) field.getAnnotation(Column.class)).name();
		} else if (field.isAnnotationPresent(JoinColumn.class)) {
			coluna = ((JoinColumn) field.getAnnotation(JoinColumn.class)).name();
		}

		if (coluna == null || coluna.isEmpty()) {
			coluna = field.getName();
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
		String sql = "SELECT * FROM " + getTableName(entityClass);
		return getResult(sql, entityClass);
	}

	
	@Override
	public Integer nextId() throws Exception {
		for (Field f : entityClass.getDeclaredFields()) {
			if (f.isAnnotationPresent(GeneratedValue.class)) {
				return nextId(f);
			}
		}
		throw new PersistenceException("Não foi encontrada a anotação GeneratedValue para a classe");
	}
	
	public Integer nextId(Field field) throws Exception {
		GeneratedValue a =  field.getAnnotation(GeneratedValue.class);
		if (a.generator() == null || a.generator().equals("")) {
			throw new PersistenceException("Não foi possível determinar o proximo sequencial. Verifique se a coluna está anotada com GeneratedValue");
		}
		
		return nextId(a.generator()); 
	}
	
	public Integer nextId(String sequence) throws Exception {		
		String sql = "select nextval('" + sequence + "') as value";
		SequenceValue sequenceValue = getSingleResult(sql, SequenceValue.class);
		return sequenceValue.getValue();
	}

	protected <S>S getSingleResult(String sql, Class<S> returnType) {
		MyBeanHandler<S> resultHandler = new MyBeanHandler<S>(returnType);
		return executeQuery(sql, resultHandler);
	}

	protected <S> List<S> getResult(String sql, Class<S> returnType) {
		MyBeanListHandler<S> resultHandler = new MyBeanListHandler<S>(returnType);
		return executeQuery(sql, resultHandler);
	}

	private <T> T executeQuery(String sql, ResultSetHandler<T> resultHandler) {
		System.out.println(sql);
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
