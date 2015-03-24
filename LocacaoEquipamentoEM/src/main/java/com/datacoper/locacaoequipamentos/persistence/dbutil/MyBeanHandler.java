package com.datacoper.locacaoequipamentos.persistence.dbutil;

import org.apache.commons.dbutils.handlers.BeanHandler;

public class MyBeanHandler<T> extends BeanHandler<T> {
	public MyBeanHandler(Class<T> type) {
		super(type, new MyBasicRowProcessor());
	}
}
