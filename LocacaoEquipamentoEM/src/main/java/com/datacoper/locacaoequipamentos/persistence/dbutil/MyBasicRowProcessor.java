package com.datacoper.locacaoequipamentos.persistence.dbutil;

import org.apache.commons.dbutils.BasicRowProcessor;

public class MyBasicRowProcessor extends BasicRowProcessor {
	public MyBasicRowProcessor() {
		super(new MyBeanProcessor());
	}
}