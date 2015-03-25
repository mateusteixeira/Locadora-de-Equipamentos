package com.dc.locacaoequipamentocommon.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class ReflectionUtils {

	public static List<Field> fieldByAnnotation(Class<?> classe, Class<? extends Annotation> annotation) {
		List<Field> fields = new LinkedList<Field>();
		if (classe.getSuperclass() != null) {
			fields.addAll(fieldByAnnotation(classe.getSuperclass(), annotation));
		}

		for (Field f : classe.getDeclaredFields()) {
			if (f.isAnnotationPresent(annotation)) {
				fields.add(f);
			}
		}

		return fields;
	}

	public static Map<String, Object> fieldByAnnotation(Class<?> classe, Object object) throws IllegalArgumentException, IllegalAccessException {
		Map<String, Object> fields = new HashMap<>();

		for (Field f : classe.getDeclaredFields()) {
			fields.put(f.getName(), getValue(f, object));
		}

		return fields;
	}

	public static Field getField(Class classeItem, String nmCampo) throws NoSuchFieldError {
		Field f = null;
		if (classeItem != null) {
			try {
				f = classeItem.getDeclaredField(nmCampo);
			} catch (NoSuchFieldException | SecurityException nsfe) {
				f = getField(classeItem.getSuperclass(), nmCampo);
			}
		}

		return f;
	}

	private static Object getValue(Field field, Object object) throws IllegalArgumentException, IllegalAccessException {
		/*
		 * Verifica se o campo está acessível, caso não esteja converte para
		 * acessivel e altera o status da flag Para que ao final do método, o
		 * status anterior seja devolvido ao campo
		 */
		Object o = null;
		boolean flagAcessible = false;
		if (!field.isAccessible()) {
			field.setAccessible(true);
			flagAcessible = true;
		}

		o = field.get(object);
		field.setAccessible(false);

		/*
		 * Volta o status de acessibildiade do capo, caso o mesmo tenha sido
		 * alterado
		 */
		if (flagAcessible) {
			field.setAccessible(!field.isAccessible());
		}
		return o;
	}

	public static Object getValueField(Class classeItem, String nmCampo, Object obj) throws NoSuchFieldError, IllegalArgumentException, IllegalAccessException {
		Field f = ReflectionUtils.getField(classeItem, nmCampo);
		return getValue(f, obj);
	}
}
