package com.datacoper.locacaoequipamentos.client.tablemodel;

import java.util.ArrayList;
import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Cliente;

public class TableModelCliente extends MyModelTable<Cliente> {

	private static List<String> listaColunas = new ArrayList<String>();

	static{
		listaColunas.add("Código");
		listaColunas.add("Nome");
		listaColunas.add("CPF");
		listaColunas.add("Telefone");
	}
	public TableModelCliente(List<Cliente> listaCliente) {
		super(listaColunas, listaCliente);
	}



	@Override
	public Object getValorObjeto(Cliente cliente, int idColuna) {
		switch (idColuna) {
		case 0:
			return cliente.getIdCliente();
		case 1:
			return cliente.getNome();
		case 2:
			return cliente.getCpf();
		case 3:
			return cliente.getTelefone();
		}
		return null;
	}

	@Override
	public void setValorAtributo(Cliente clienteSelecionado, Object novoValor, int indiceAtributo) {

		switch (indiceAtributo) {
		case 0:
			clienteSelecionado.setIdCliente((int) novoValor);
		case 1:
			clienteSelecionado.setNome((String) novoValor);
		case 2:
			clienteSelecionado.setCpf((String) novoValor);
		case 3:
			clienteSelecionado.setTelefone((String) novoValor);
		}

	}

}
