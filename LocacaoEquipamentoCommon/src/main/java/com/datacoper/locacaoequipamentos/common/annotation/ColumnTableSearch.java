package com.datacoper.locacaoequipamentos.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.datacoper.locacaoequipamentos.common.model.enums.Comparador;

@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnTableSearch {
	String header();

	int width() default 0;

	boolean isFilter() default true;

	String FilterTitle() default "";
	
	Comparador comparador() default Comparador.IGUAL;

	int index() default 0;
}
