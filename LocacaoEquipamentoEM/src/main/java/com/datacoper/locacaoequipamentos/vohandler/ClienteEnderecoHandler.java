package com.datacoper.locacaoequipamentos.vohandler;

import java.util.ArrayList;
import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Cliente;
import com.datacoper.locacaoequipamentos.common.model.Endereco;
import com.dc.locacaoequipamentocommon.vo.ClienteEnderecoVO;

public class ClienteEnderecoHandler {

	public static List<Cliente> getMultipleResult(List<ClienteEnderecoVO> lista) {
		List<Cliente> clientes = new ArrayList<Cliente>();
		for (ClienteEnderecoVO clienteEnderecoVO : lista) {
			Cliente cliente = new Cliente();
			cliente.setIdCliente(clienteEnderecoVO.getIdCliente());
			cliente.setNome(clienteEnderecoVO.getNome());
			cliente.setRg(clienteEnderecoVO.getRg());
			cliente.setCpf(clienteEnderecoVO.getCpf());
			cliente.setEstadoCivil(clienteEnderecoVO.getEstadoCivil());
			cliente.setSexo(clienteEnderecoVO.getSexo());
			cliente.setEmail(clienteEnderecoVO.getEmail());
			cliente.setTelefone(clienteEnderecoVO.getTelefone());
			cliente.setDataNascimento(clienteEnderecoVO.getDataNascimento());
			cliente.setDataCadastro(clienteEnderecoVO.getDataCadastro());
			Endereco endereco = new Endereco();
			endereco.setEstado(clienteEnderecoVO.getEstado());
			endereco.setCidade(clienteEnderecoVO.getCidade());
			endereco.setRua(clienteEnderecoVO.getRua());
			endereco.setBairro(clienteEnderecoVO.getBairro());
			endereco.setCep(clienteEnderecoVO.getCep());
			endereco.setNumero(clienteEnderecoVO.getNumero());
			endereco.setComplemento(clienteEnderecoVO.getComplemento());
			cliente.setEndereco(endereco);

			clientes.add(cliente);

		}
		return clientes;
	}

	public static Cliente getSingleResult(ClienteEnderecoVO clienteEnderecoVO) {
		Cliente cliente = new Cliente();
		cliente.setIdCliente(clienteEnderecoVO.getIdCliente());
		cliente.setNome(clienteEnderecoVO.getNome());
		cliente.setRg(clienteEnderecoVO.getRg());
		cliente.setCpf(clienteEnderecoVO.getCpf());
		cliente.setEstadoCivil(clienteEnderecoVO.getEstadoCivil());
		cliente.setSexo(clienteEnderecoVO.getSexo());
		cliente.setEmail(clienteEnderecoVO.getEmail());
		cliente.setTelefone(clienteEnderecoVO.getTelefone());
		cliente.setDataNascimento(clienteEnderecoVO.getDataNascimento());
		cliente.setDataCadastro(clienteEnderecoVO.getDataCadastro());
		Endereco endereco = new Endereco();
		endereco.setEstado(clienteEnderecoVO.getEstado());
		endereco.setCidade(clienteEnderecoVO.getCidade());
		endereco.setRua(clienteEnderecoVO.getRua());
		endereco.setBairro(clienteEnderecoVO.getBairro());
		endereco.setCep(clienteEnderecoVO.getCep());
		endereco.setNumero(clienteEnderecoVO.getNumero());
		endereco.setComplemento(clienteEnderecoVO.getComplemento());
		cliente.setEndereco(endereco);

		return cliente;
	}

}
