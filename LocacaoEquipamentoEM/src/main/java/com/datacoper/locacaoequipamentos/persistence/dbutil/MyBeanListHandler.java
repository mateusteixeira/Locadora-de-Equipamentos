package com.datacoper.locacaoequipamentos.persistence.dbutil;

import org.apache.commons.dbutils.handlers.BeanListHandler;

public class MyBeanListHandler<T> extends BeanListHandler<T> {

	public MyBeanListHandler(Class<T> type) {
		super(type, new MyBasicRowProcessor());
	}
}