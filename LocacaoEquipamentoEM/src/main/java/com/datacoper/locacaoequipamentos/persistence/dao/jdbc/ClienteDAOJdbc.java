/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datacoper.locacaoequipamentos.persistence.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.datacoper.locacaoequipamentos.common.model.Cliente;
import com.datacoper.locacaoequipamentos.persistence.dao.interfaces.ClienteDAO;
import com.datacoper.locacaoequipamentos.vohandler.ClienteEnderecoHandler;
import com.dc.locacaoequipamentocommon.vo.ClienteEnderecoVO;

/**
 *
 * @author Java
 */
public class ClienteDAOJdbc extends GenericDAOJdbc implements ClienteDAO {

	static final String fk_locacao = "fk_locacao";
	// Nome das colunas do banco (locacao)
	static final String id_locacao = "id_locacao";
	static final String nome_dep = "nome";
	static final String fk_cliente = "fk_cliente";
	// Nome das colunas do banco (cliente)
	static final String idCliente = "idcliente";
	static final String nome = "nome";
	static final String rg = "rg";
	static final String cpf = "cpf";
	static final String estadocivil = "estadocivil";
	static final String sexo = "sexo";
	static final String email = "email";
	static final String telefone = "telefone";
	static final String datanascimento = "datanascimento";
	static final String datacadastro = "datacadastro";
	static final String estado = "estado";
	static final String cidade = "cidade";
	static final String rua = "rua";
	static final String cep = "cep";
	static final String numero = "numero";
	static final String complemento = "complemento";
	static final String bairro = "bairro";

	public ClienteDAOJdbc(Connection connection) {
		super(connection);
	}

	@Override
	public void insert(Cliente cliente) {

		String insertSQL = "INSERT INTO cliente (idcliente, nome, rg, cpf, estadocivil, sexo, email, telefone, datanascimento, datacadastro, "
				+ "estado, cidade, rua, bairro, cep, numero, complemento) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(insertSQL);
			ps.setInt(1, cliente.getIdCliente());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getRg());
			ps.setString(4, cliente.getCpf());
			ps.setString(5, String.valueOf(cliente.getEstadoCivil()));
			ps.setString(6, String.valueOf(cliente.getSexo()));
			ps.setString(7, cliente.getEmail());
			ps.setString(8, cliente.getTelefone());
			ps.setString(9, cliente.getDataNascimento());
			ps.setString(10, cliente.getDataCadastro());
			ps.setString(11, cliente.getEndereco().getEstado());
			ps.setString(12, cliente.getEndereco().getCidade());
			ps.setString(13, cliente.getEndereco().getRua());
			ps.setString(14, cliente.getEndereco().getBairro());
			ps.setString(15, cliente.getEndereco().getCep());
			ps.setInt(16, cliente.getEndereco().getNumero());
			ps.setString(17, cliente.getEndereco().getComplemento());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null)
				closeResource(ps);
		}

	}

	@Override
	public Integer nextId() {
		return getNextSequenceValue("idcliente_pk_seq");
	}

	@Override
	public List<Cliente> encontrarClienteAll() {
		String sqlSearch = "SELECT * FROM cliente";
		List<ClienteEnderecoVO> clientes = getResult(sqlSearch, ClienteEnderecoVO.class);
		return ClienteEnderecoHandler.getMultipleResult(clientes);
	}

	@Override
	public List<Cliente> pesquisar(String campoPesquisar, String pesquisa) {
		String tag = null;
		String operador = null;
		switch (campoPesquisar) {
		case "nome": {
			tag = nome;
			operador = "LIKE";
			break;
		}
		case "codigo": {
			tag = idCliente;
			operador = "=";
			break;
		}
		case "cpf": {
			tag = cpf;
			operador = "LIKE";
			break;
		}
		case "telefone": {
			tag = telefone;
			operador = "LIKE";
			break;
		}
		default: {
			tag = nome;
			operador = "LIKE";
		}
		}

		String sqlSearch = "SELECT * FROM cliente WHERE UPPER(" + tag + ") " + operador + " UPPER('%" + pesquisa + "%')";
		System.out.println(sqlSearch);
		List<ClienteEnderecoVO> clientes = getResult(sqlSearch, ClienteEnderecoVO.class);
		return ClienteEnderecoHandler.getMultipleResult(clientes);
	}

	@Override
	public void excluir(Cliente cliente) {
		String sqlString = "DELETE FROM cliente WHERE " + idCliente + " = " + cliente.getIdCliente();
		System.out.println(sqlString);
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(sqlString);
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null)
				closeResource(ps);
		}

	}

	@Override
	public void update(Cliente cliente) {
		
		String insertSQL = "UPDATE cliente SET nome = ?, rg = ?, cpf = ?, estadocivil= ?, sexo= ?, email= ?, telefone= ?, datanascimento= ?, datacadastro= ?, "
				+ "estado= ?, cidade= ?, rua= ?, bairro= ?, cep= ?, numero= ?, complemento= ? WHERE idcliente = " + cliente.getIdCliente();

		
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(insertSQL);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getRg());
			ps.setString(3, cliente.getCpf());
			ps.setString(4, String.valueOf(cliente.getEstadoCivil()));
			ps.setString(5, String.valueOf(cliente.getSexo()));
			ps.setString(6, cliente.getEmail());
			ps.setString(7, cliente.getTelefone());
			ps.setString(8, cliente.getDataNascimento());
			ps.setString(9, cliente.getDataCadastro());
			ps.setString(10, cliente.getEndereco().getEstado());
			ps.setString(11, cliente.getEndereco().getCidade());
			ps.setString(12, cliente.getEndereco().getRua());
			ps.setString(13, cliente.getEndereco().getBairro());
			ps.setString(14, cliente.getEndereco().getCep());
			ps.setInt(15, cliente.getEndereco().getNumero());
			ps.setString(16, cliente.getEndereco().getComplemento());
			System.out.println(ps.toString());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (ps != null)
				closeResource(ps);
		}

	}

	// public List<Cliente> buscaEsp(int ordem, int ascDesc, String cont) {
	// String ord, a_d;
	// switch (ordem) {
	// case 1:
	// ord = nome;
	// break;
	// case 2:
	// ord = id;
	// break;
	// case 3:
	// ord = cpf;
	// break;
	// case 4:
	// ord = telefone;
	// break;
	// default:
	// ord = id;
	// break;
	// }
	// switch (ascDesc) {
	// case 1:
	// a_d = "ASC";
	// break;
	// case 2:
	// a_d = "DESC";
	// break;
	// default:
	// a_d = "ASC";
	// break;
	// }
	//
	// ResultSet rs = null, rs2;
	// switch (ID) {
	// case 1: { // rs =
	// FabricaDeConexao.getInstance().pesquisa(
	// "SELECT to_char(" + datanascimento + ",'dd/MM/yyyy'),to_char(" +
	// datacadastro + ",'dd/MM/yyyy'), FROM cliente WHERE " + nome + " ~ '"
	// + cont + "' ORDER BY " + ord + " " + a_d + "");
	// rs =
	// ConnectionController.getInstance().pesquisa("SELECT  FROM pessoa WHERE "
	// + nome + " ~ '" + cont + "' ORDER BY " + ord + " " + a_d + "");
	// }
	// break;
	// case 2: {
	// rs =
	// ConnectionController.getInstance().pesquisa("SELECT  FROM pessoa WHERE "
	// + id + " = " + cont + " ORDER BY " + ord + " " + a_d + "");
	// }
	// break;
	// case 3: {
	// rs =
	// ConnectionController.getInstance().pesquisa("SELECT  FROM pessoa WHERE "
	// + cpf + " ~ '" + cont + "' ORDER BY " + ord + " " + a_d + "");
	// }
	// break;
	// case 4: {
	// rs =
	// ConnectionController.getInstance().pesquisa("SELECT  FROM pessoa WHERE "
	// + telefone + " ~ '" + cont + "' ORDER BY " + ord + " " + a_d + "");
	// }
	// break;
	// default:
	// System.out.println("Insira um identificador correto, 1-nome, 2-id, 3-cpf, 4-telefone");
	// break;
	// }
	// }

	/*
	 * public void insert(Cliente cliente) { ResultSet rs2; rs2 =
	 * ConnectionController
	 * .getInstance().pesquisa("select nextval('pessoa_id_seq') as value"); try
	 * { rs2.next(); } catch (SQLException ex) {
	 * Logger.getLogger(ClienteDAOJdbc.class.getName()).log(Level.SEVERE, null,
	 * ex); throw new RuntimeException("Erro ao pegar nextVal rs2 - insert",
	 * ex); } int nextval; try { nextval = rs2.getInt(1); } catch (SQLException
	 * ex) { Logger.getLogger(ClienteDAOJdbc.class.getName()).log(Level.SEVERE,
	 * null, ex); throw new
	 * RuntimeException("Erro ao pegar nextVal rs2 - insert", ex); } nextval =
	 * nextval + 1; int rs; if (nextval > 0) { rs =
	 * ConnectionController.getInstance
	 * ().insere("INSERT INTO pessoa1 VALUES (default,'" + cliente.getNome() +
	 * "', '" + cliente.getRg() + "','" + cliente.getCpf() + "','" +
	 * cliente.getEstadoCivil() + "','" + cliente.getSexo() + "','" +
	 * cliente.getEmail() + "','" + cliente.getTelefone() + "','" +
	 * cliente.getDataNascimento() + "','" + cliente.getDataCadastro() + "','" +
	 * cliente.getEndereco().getEstado() + "','" +
	 * cliente.getEndereco().getCidade() + "','" +
	 * cliente.getEndereco().getRua() + "','" +
	 * cliente.getEndereco().getBairro() + "','" +
	 * cliente.getEndereco().getCep() + "','" +
	 * cliente.getEndereco().getNumero() + "','" +
	 * cliente.getEndereco().getComplemento() + "', default" + ")"); if (rs > 0)
	 * { System.out.println("Cadastro realizado com sucesso!"); return true; }
	 * else { new Throwable("Erro: daoCliente - insert - Insercao do cliente");
	 * System.out.println("Erro: daoCliente - insert - Insercao do cliente");
	 * System.out.println("INSERT INTO pessoa VALUES (default,'" +
	 * cliente.getNome() + "', '" + cliente.getRg() + "','" + cliente.getCpf() +
	 * "','" + cliente.getEstadoCivil() + "','" + cliente.getSexo() + "','" +
	 * cliente.getEmail() + "','" + cliente.getTelefone() + "','" +
	 * cliente.getDataNascimento() + "','" + cliente.getDataCadastro() + "','" +
	 * cliente.getEndereco().getEstado() + "','" +
	 * cliente.getEndereco().getCidade() + "','" +
	 * cliente.getEndereco().getRua() + "','" +
	 * cliente.getEndereco().getBairro() + "','" +
	 * cliente.getEndereco().getCep() + "','" +
	 * cliente.getEndereco().getNumero() + "','" +
	 * cliente.getEndereco().getComplemento() + "', default" + ")"); return
	 * false; } } else { new Throwable(
	 * "Erro: daoCliente - insert - Nao foi possivel encontrar o nextval de cliente"
	 * ); System.out.println(
	 * "Erro: daoCliente - insert - Nao foi possivel encontrar o nextval de cliente"
	 * ); return false; } }
	 * 
	 * public List<Cliente> buscaAll(int ordem, int ascDesc) { String ord, a_d;
	 * 
	 * switch (ordem) { case 1: ord = nome; break; case 2: ord = id; break; case
	 * 3: ord = cpf; break; case 4: ord = telefone; break; default: ord = id;
	 * break; } switch (ascDesc) { case 1: a_d = "ASC"; break; case 2: a_d =
	 * "DESC"; break; default: a_d = "ASC"; break; }
	 * 
	 * ResultSet rs, rs2; rs =
	 * ConnectionController.getInstance().pesquisa("SELECT * FROM pessoa ORDER BY "
	 * + ord + " " + a_d + ""); // rs =
	 * FabricaDeConexao.getInstance().pesquisa("SELECT to_char(" +
	 * datanascimento + ",'dd/MM/yyyy'),to_char(" + datacadastro +
	 * ",'dd/MM/yyyy'),* FROM pessoa ORDER BY " + ord + " " + a_d + ""); //
	 * System.out.println("SELECT to_char(" + datanascimento +
	 * ",'dd/MM/yyyy'),to_char(" + datacadastro +
	 * ",'dd/MM/yyyy'),* FROM cliente ORDER BY " + ord + " " + a_d + "");
	 * List<Cliente> lista = new ArrayList<Cliente>();
	 * 
	 * try { while (rs.next()) { Cliente temp = new Cliente(); // Monto o objeto
	 * cliente temp.setIdCliente(rs.getInt(id));
	 * temp.setNome(rs.getString(nome)); temp.setRg(rs.getString(rg));
	 * temp.setCpf(rs.getString(cpf));
	 * temp.setEstadoCivil(rs.getString(estadocivil));
	 * temp.setSexo(rs.getString(sexo)); temp.setEmail(rs.getString(email));
	 * temp.setTelefone(rs.getString(telefone));
	 * temp.setDataNascimento(rs.getString(datanascimento));
	 * temp.setDataCadastro(rs.getString(datacadastro)); Endereco end = new
	 * Endereco(); end.setEstado(rs.getString(estado));
	 * end.setCidade(rs.getString(cidade)); end.setRua(rs.getString(rua));
	 * end.setBairro(rs.getString(bairro)); end.setCep(rs.getString(cep));
	 * end.setNumero(rs.getInt(numero));
	 * end.setComplemento(rs.getString(complemento)); temp.setEndereco(end);
	 * lista.add(temp); } } catch (SQLException ex) {
	 * Logger.getLogger(ClienteDAOJdbc.class.getName()).log(Level.SEVERE, null,
	 * ex); throw new
	 * RuntimeException("Erro ao pegar next de resultSet - buscaAll", ex); }
	 * return lista;
	 * 
	 * }
	 * 
	 * public List<Cliente> buscaEsp(int ordem, int ascDesc, int ID, String
	 * cont) { String ord, a_d; switch (ordem) { case 1: ord = nome; break; case
	 * 2: ord = id; break; case 3: ord = cpf; break; case 4: ord = telefone;
	 * break; default: ord = id; break; } switch (ascDesc) { case 1: a_d =
	 * "ASC"; break; case 2: a_d = "DESC"; break; default: a_d = "ASC"; break; }
	 * 
	 * ResultSet rs = null, rs2; switch (ID) { case 1: { // rs =
	 * FabricaDeConexao.getInstance().pesquisa("SELECT to_char(" +
	 * datanascimento + ",'dd/MM/yyyy'),to_char(" + datacadastro +
	 * ",'dd/MM/yyyy'),* FROM cliente WHERE " + nome + " ~* '" + cont +
	 * "' ORDER BY " + ord + " " + a_d + ""); rs =
	 * ConnectionController.getInstance().pesquisa("SELECT * FROM pessoa WHERE "
	 * + nome + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + ""); } break;
	 * case 2: { rs =
	 * ConnectionController.getInstance().pesquisa("SELECT * FROM pessoa WHERE "
	 * + id + " = " + cont + " ORDER BY " + ord + " " + a_d + ""); } break; case
	 * 3: { rs =
	 * ConnectionController.getInstance().pesquisa("SELECT * FROM pessoa WHERE "
	 * + cpf + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + ""); } break;
	 * case 4: { rs =
	 * ConnectionController.getInstance().pesquisa("SELECT * FROM pessoa WHERE "
	 * + telefone + " ~* '" + cont + "' ORDER BY " + ord + " " + a_d + ""); }
	 * break; default: System.out.println(
	 * "Insira um identificador correto, 1-nome, 2-id, 3-cpf, 4-telefone");
	 * break; }
	 * 
	 * List<Cliente> lista = new ArrayList<Cliente>();
	 * 
	 * try { while (rs.next()) { Cliente temp = new Cliente(); // Monto o objeto
	 * cliente temp.setIdCliente(rs.getInt(id));
	 * temp.setNome(rs.getString(nome)); temp.setRg(rs.getString(rg));
	 * temp.setCpf(rs.getString(cpf));
	 * temp.setEstadoCivil(rs.getString(estadocivil));
	 * temp.setSexo(rs.getString(sexo)); temp.setEmail(rs.getString(email));
	 * temp.setTelefone(rs.getString(telefone));
	 * temp.setDataNascimento(rs.getString(datanascimento));
	 * temp.setDataCadastro(rs.getString(datacadastro)); Endereco end = new
	 * Endereco(); end.setEstado(rs.getString(estado));
	 * end.setCidade(rs.getString(cidade)); end.setRua(rs.getString(rua));
	 * end.setBairro(rs.getString(bairro)); end.setCep(rs.getString(cep));
	 * end.setNumero(rs.getInt(numero));
	 * end.setComplemento(rs.getString(complemento)); temp.setEndereco(end);
	 * lista.add(temp); } } catch (SQLException ex) {
	 * Logger.getLogger(ClienteDAOJdbc.class.getName()).log(Level.SEVERE, null,
	 * ex); throw new
	 * RuntimeException("Erro ao pegar next de resultSet - buscaEsp", ex); }
	 * return lista; }
	 * 
	 * public boolean updateCliente(Cliente c) {
	 * System.out.println("UPDATE pessoa SET " + nome + "='" + c.getNome() +
	 * "'," + rg + "='" + c.getRg() + "'," + cpf + "='" + c.getCpf() + "'," +
	 * estadocivil + "='" + c.getEstadoCivil() + "'," + sexo + "='" +
	 * c.getSexo() + "'," + email + "='" + c.getEmail() + "'," + telefone + "='"
	 * + c.getTelefone() + "'," + datanascimento + "='" + c.getDataNascimento()
	 * + "'," + datacadastro + "='" + c.getDataCadastro() + "'," + estado + "='"
	 * + c.getEndereco().getEstado() + "'," + cidade + "='" +
	 * c.getEndereco().getCidade() + "'," + rua + "='" +
	 * c.getEndereco().getRua() + "'," + bairro + "='" +
	 * c.getEndereco().getBairro() + "'," + cep + "='" +
	 * c.getEndereco().getCep() + "'," + numero + "=" +
	 * c.getEndereco().getNumero() + "," + complemento + "='" +
	 * c.getEndereco().getComplemento() + "'" + " WHERE " + id + "=" +
	 * c.getIdCliente()); int rs; rs =
	 * ConnectionController.getInstance().insere("UPDATE pessoa SET " + nome +
	 * "='" + c.getNome() + "'," + rg + "='" + c.getRg() + "'," + cpf + "='" +
	 * c.getCpf() + "'," + estadocivil + "='" + c.getEstadoCivil() + "'," + sexo
	 * + "='" + c.getSexo() + "'," + email + "='" + c.getEmail() + "'," +
	 * telefone + "='" + c.getTelefone() + "'," + datanascimento + "='" +
	 * c.getDataNascimento() + "'," + datacadastro + "='" + c.getDataCadastro()
	 * + "'," + estado + "='" + c.getEndereco().getEstado() + "'," + cidade +
	 * "='" + c.getEndereco().getCidade() + "'," + rua + "='" +
	 * c.getEndereco().getRua() + "'," + bairro + "='" +
	 * c.getEndereco().getBairro() + "'," + cep + "='" +
	 * c.getEndereco().getCep() + "'," + numero + "=" +
	 * c.getEndereco().getNumero() + "," + complemento + "='" +
	 * c.getEndereco().getComplemento() + "'" + " WHERE " + id + "=" +
	 * c.getIdCliente()); if (rs > 0) {
	 * System.out.println("Cliente Editado com Sucesso!"); return true; } else {
	 * System.out.println("Falha ao Editar Cliente");
	 * System.out.println("UPDATE pessoa SET " + nome + "='" + c.getNome() +
	 * "'," + rg + "='" + c.getRg() + "'," + cpf + "='" + c.getCpf() + "'," +
	 * estadocivil + "='" + c.getEstadoCivil() + "'," + sexo + "='" +
	 * c.getSexo() + "'," + email + "='" + c.getEmail() + "'," + telefone + "='"
	 * + c.getTelefone() + "'," + datanascimento + "='" + c.getDataNascimento()
	 * + "'," + datacadastro + "='" + c.getDataCadastro() + "'," + estado + "='"
	 * + c.getEndereco().getEstado() + "'," + cidade + "='" +
	 * c.getEndereco().getCidade() + "'," + rua + "='" +
	 * c.getEndereco().getRua() + "'," + bairro + "='" +
	 * c.getEndereco().getBairro() + "'," + cep + "='" +
	 * c.getEndereco().getCep() + "'," + numero + "=" +
	 * c.getEndereco().getNumero() + "," + complemento + "='" +
	 * c.getEndereco().getComplemento() + "'" + " WHERE " + id + "=" +
	 * c.getIdCliente());
	 * 
	 * return false; } }
	 * 
	 * public boolean excluiCliente(Cliente c) {
	 * System.out.println("DELETE FROM pessoa WHERE " + id + "=" +
	 * c.getIdCliente()); int rs; rs =
	 * ConnectionController.getInstance().insere("DELETE FROM pessoa WHERE " +
	 * id + "=" + c.getIdCliente()); if (rs > 0) {
	 * System.out.println("Cliente Editado com Sucesso!"); return true; } else {
	 * System.out.println("Falha ao Editar Cliente");
	 * System.out.println("DELETE FROM pessoa WHERE " + id + "=" +
	 * c.getIdCliente()); return false; } }
	 */
}
