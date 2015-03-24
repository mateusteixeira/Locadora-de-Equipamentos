package com.datacoper.locacaoequipamentos.client.cliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.datacoper.locacaoequipamentos.client.formspadrao.FormPadraoPesquisa;
import com.datacoper.locacaoequipamentos.client.tablemodel.MyModelTable;
import com.datacoper.locacaoequipamentos.client.tablemodel.TableModelCliente;
import com.datacoper.locacaoequipamentos.common.model.Cliente;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.service.ClienteService;
import com.datacoper.locacaoequipamentos.common.service.ServiceLocator;

public class FormBuscaCliente /*extends FormPadraoPesquisa*/ {

	/*private static final long serialVersionUID = 1L;

	private ClienteService clienteService;
	private List<Cliente> listaClientes;

	public FormBuscaCliente() {
		super(Cliente.class, "Nome", "Código", "CPF", "Telefone");
		clienteService = new ServiceLocator().loadService(ClienteService.class);
	}


	public void ok() {
		super.ok();
	}


	public void cancelar() {
		super.cancelar();
	}

	@Override
	public TableModel getTableModel(List listaClientes) {

		TableModel tableModel = new TableModelCliente(listaClientes);
		return tableModel;
	}

	@Override
	public List<Cliente> pesquisar(String pesquisa) {
		if (pesquisa == null || pesquisa.isEmpty()) {
			List<Cliente> l = clienteService.encontrarTodosClientes();
			for (Cliente cliente : l) {
				System.out.println("Sexo = " + cliente.getSexo().getDescricao());
			}
			return l;
		} else {
			return clienteService.encontrarClienteEsp(comboBox.getSelectedIndex(), pesquisa);
		}
	}*/
}
