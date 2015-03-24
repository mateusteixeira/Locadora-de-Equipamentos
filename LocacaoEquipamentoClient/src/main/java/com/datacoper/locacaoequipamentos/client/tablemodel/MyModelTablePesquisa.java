package com.datacoper.locacaoequipamentos.client.tablemodel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;

public class MyModelTablePesquisa<T extends Object> extends AbstractTableModel {
	private List<T> valores;
	private List<Coluna> colunas;

	// Esse é um construtor, que recebe a nossa lista de objetos
	public MyModelTablePesquisa(List<T> valores) {
		this.valores = new ArrayList<T>(valores);

		List<Annotation> c = annotationByAnnotation(valores.get(0).getClass(), (Class<? extends Annotation>) ColumnTableSearch.class);
		//colunas.add(new Coluna(f.getName(), a.header(), a.width()));
	}

	public List<Field> fieldByAnnotation(Class<?> classe, Class<? extends Annotation> annotation) {
		List<Field> fields = new LinkedList<Field>();
		if (classe.getSuperclass() != null) {
			fields.addAll(fieldByAnnotation(classe.getSuperclass(), annotation));
		}

		Class c = valores.get(0).getClass();

		for (Field f : c.getDeclaredFields()) {
			if (f.isAnnotationPresent(annotation)) {
				fields.add(f);
			}
		}

		return fields;
	}

	public List<Annotation> annotationByAnnotation(Class<?> classe, Class<? extends Annotation> annotation) {
		List<Annotation> lista = new LinkedList<>();

		for (Field f : fieldByAnnotation(classe, annotation)) {
			lista.add(f.getAnnotation(annotation));
		}

		return lista;

	}

	public int getRowCount() {
		// Quantas linhas tem.
		return valores.size();
	}

	public int getColumnCount() {
		// Quantas colunas tem.
		return colunas.size();
	}

	public String getColumnName(int column) {
		// nome das das colunas
		return colunas.get(column).getHeader();
	}

	public Object getValueAt(int row, int column) {
		String label = colunas.get(column).getNomeFisico();

		Object o = null;
		try {
			Field f = valores.get(row).getClass().getClass().getField(label);
			f.setAccessible(true);
			o = f.get(valores.get(row));
			f.setAccessible(false);
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return o;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		T objeto = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		// setValorAtributo(objeto, aValue, columnIndex);
	}

	public Class<?> getColumnClass(int columnIndex) {
		// Qual a classe das nossas colunas? Como estamos exibindo texto, é
		// string.
		return String.class;
	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public T get(int row) {
		return valores.get(row);
	}

	protected class Coluna {
		private String nomeFisico;
		private String header;
		private int width;

		public Coluna(String nomeFisico, String header, int width) {
			this.nomeFisico = nomeFisico;
			this.header = header;
			this.width = width;
		}

		public String getNomeFisico() {
			return nomeFisico;
		}

		public void setNomeFisico(String nomeFisico) {
			this.nomeFisico = nomeFisico;
		}

		public String getHeader() {
			return header;
		}

		public void setHeader(String header) {
			this.header = header;
		}

		public int getWidth() {
			return width;
		}

		public void setWidth(int width) {
			this.width = width;
		}
	}
}