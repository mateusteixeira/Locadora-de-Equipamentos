package com.datacoper.locacaoequipamentos.client.tablemodel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;
import com.datacoper.locacaoequipamentos.common.util.ReflectionUtils;

public class MyModelTablePesquisa<T extends Object> extends AbstractTableModel {
	private List<T> valores;
	private List<Coluna> colunas;
	private Class classePesquisa;

	// Esse é um construtor, que recebe a nossa lista de objetos
	public MyModelTablePesquisa(Class<T> classePesquisa, List<T> valores) {
		this.classePesquisa = classePesquisa;
		this.valores = valores;
		colunas = new LinkedList<>();
		List<Field> fields = ReflectionUtils.getFieldByAnnotation(classePesquisa, (Class<? extends Annotation>) ColumnTableSearch.class);

		for (Field f : fields) {
			ColumnTableSearch a = f.getAnnotation(ColumnTableSearch.class);
			colunas.add(new Coluna(f.getName(), a.header(), a.width()));
		}
	}

	public int getRowCount() {
		// Quantas linhas tem.
		if (valores != null && !valores.isEmpty()) {
			return valores.size();
		} else {
			return 0;
		}
	}

	public int getColumnCount() {
		// Quantas colunas tem.
		if (colunas != null && !colunas.isEmpty()) {
			return colunas.size();
		}
		return 0;
	}

	public String getColumnName(int column) {
		// nome das das colunas
		return colunas.get(column).getHeader();
	}
	
	public Object getValueAt(int row, int column) {
		String label = colunas.get(column).getNomeFisico();

		try {
			return ReflectionUtils.getValueField(classePesquisa, label, valores.get(row));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
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
		if (row > -1) {
			return valores.get(row);
		}

		return null;
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