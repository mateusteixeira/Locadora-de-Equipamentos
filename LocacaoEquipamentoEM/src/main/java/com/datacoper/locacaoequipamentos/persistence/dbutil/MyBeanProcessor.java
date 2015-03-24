package com.datacoper.locacaoequipamentos.persistence.dbutil;

import java.beans.PropertyDescriptor;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.BeanProcessor;

public class MyBeanProcessor extends BeanProcessor {
	public MyBeanProcessor() {
		this(new HashMap<String, String>());
	}
	/**
     * ResultSet column to bean property name overrides.
     */
    private final Map<String, String> columnToPropertyOverrides;
	
	public MyBeanProcessor(Map<String, String> columnToPropertyOverrides) {
        super(columnToPropertyOverrides);
        if (columnToPropertyOverrides == null) {
            throw new IllegalArgumentException("columnToPropertyOverrides map cannot be null");
        }
        this.columnToPropertyOverrides = columnToPropertyOverrides;
    }
	
	@Override
	protected int[] mapColumnsToProperties(ResultSetMetaData rsmd,
			PropertyDescriptor[] props) throws SQLException {
		int cols = rsmd.getColumnCount();
        int[] columnToProperty = new int[cols + 1];
        Arrays.fill(columnToProperty, PROPERTY_NOT_FOUND);

        for (int col = 1; col <= cols; col++) {
        	
        	/*Implementação manual*/
        	String columnName = rsmd.getColumnLabel(col);
        	while(true) {
        		int pos = columnName.indexOf("_");
        		if (pos == -1) break;
        		columnName = columnName.substring(0, pos) + Character.toUpperCase(columnName.charAt(pos+1)) + columnName.substring(pos+2, columnName.length());
        	}
        	/*####################*/
        	 
            if (null == columnName || 0 == columnName.length()) {
              columnName = rsmd.getColumnName(col);
            }
            String propertyName = columnToPropertyOverrides.get(columnName);
            if (propertyName == null) {
                propertyName = columnName;
            }
            for (int i = 0; i < props.length; i++) {

                if (propertyName.equalsIgnoreCase(props[i].getName())) {
                    columnToProperty[col] = i;
                    break;
                }
            }
        }

        return columnToProperty;
	}
}
