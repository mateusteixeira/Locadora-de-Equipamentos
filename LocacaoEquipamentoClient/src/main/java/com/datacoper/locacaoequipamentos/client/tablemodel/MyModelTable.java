package com.datacoper.locacaoequipamentos.client.tablemodel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class MyModelTable<T extends Object> extends AbstractTableModel {
	private List<T> valores;
	private List<String> colunas;

	// Esse é um construtor, que recebe a nossa lista de objetos
	public MyModelTable(List<String> colunas, List<T> valores) {
		this.valores = new ArrayList<T>(valores);
		this.colunas = new ArrayList<String>(colunas);
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
		return colunas.get(column);
	}

	public Object getValueAt(int row, int column) {

		if (row < valores.size())
			return getValorObjeto(valores.get(row), column);
		return null;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		T objeto = valores.get(rowIndex);
		// Vamos alterar o valor da coluna columnIndex na linha rowIndex com o
		// valor aValue passado no parâmetro.
		// Note que vc poderia alterar 2 campos ao invés de um só.
		setValorAtributo(objeto, aValue, columnIndex);
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

	public abstract Object getValorObjeto(T objeto, int idColuna);

	public abstract void setValorAtributo(T objetoSelecionado, Object novoValor, int indiceAtributo);
}