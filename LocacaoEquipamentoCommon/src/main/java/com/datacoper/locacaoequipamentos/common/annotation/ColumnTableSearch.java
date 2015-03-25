package com.datacoper.locacaoequipamentos.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnTableSearch {
	String header();
	int width();
}
