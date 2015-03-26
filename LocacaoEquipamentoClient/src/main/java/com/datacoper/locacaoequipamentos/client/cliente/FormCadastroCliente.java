	package com.datacoper.locacaoequipamentos.client.cliente;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.datacoper.locacaoequipamentos.client.formspadrao.FormPadraoCadastro;
import com.datacoper.locacaoequipamentos.client.util.ViewMethods;
import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;
import com.datacoper.locacaoequipamentos.common.service.interfaces.ClienteService;

public class FormCadastroCliente extends FormPadraoCadastro {

	private static final long serialVersionUID = 1L;

	private JTextField idClienteField;
	private JTextField dataCadastroField;
	private JTextField nomeClienteField;
	private JTextField cpfClienteField;
	private JTextField rgClienteField;
	private static com.toedter.calendar.JDateChooser nascimentoClienteField;
	private JTextField telefoneClienteField;
	private JTextField emailClienteField;
	private JTextField cidadeClienteField;
	private JTextField ruaClienteField;
	private JTextField bairroClienteField;
	private JTextField complementoClienteField;
	private JTextField numeroClienteField;
	private JTextField cepClienteField;
	private JComboBox sexoClienteBox;
	private JComboBox estadoCivilClienteBox;
	private JComboBox estadoClienteBox;

	private SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date(System.currentTimeMillis());
	private ClienteService clienteService;
	private JPanel panelPessoa;
	private JPanel panelEndereco;

	public FormCadastroCliente() {
		super();
		panelDados.setLayout(null);
		init();

		getClienteService();
		setComponentsDisable();
	}

	private void setComponentsDisable() {
		ViewMethods.chaveadorCampos(panelEndereco, false);
		ViewMethods.chaveadorCampos(panelPessoa, false);

	}

	private void init() {
		panelPessoa = new JPanel();
		panelPessoa.setBorder(new TitledBorder(null, "Pessoa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPessoa.setBounds(10, 38, 452, 155);
		panelDados.add(panelPessoa);
		panelPessoa.setLayout(null);

		JLabel lblnome = new JLabel("*Nome:");
		lblnome.setBounds(10, 24, 37, 14);
		panelPessoa.add(lblnome);

		JLabel lblcpf = new JLabel("*CPF:");
		lblcpf.setBounds(290, 24, 29, 14);
		panelPessoa.add(lblcpf);

		JLabel lblNewLabel_1 = new JLabel("*RG:");
		lblNewLabel_1.setBounds(10, 52, 29, 14);
		panelPessoa.add(lblNewLabel_1);

		JLabel lbldatanascimento = new JLabel("*DataNascimento:");
		lbldatanascimento.setBounds(257, 114, 88, 14);
		panelPessoa.add(lbldatanascimento);

		nomeClienteField = new JTextField();
		nomeClienteField.setBounds(57, 21, 223, 20);
		panelPessoa.add(nomeClienteField);
		nomeClienteField.setColumns(10);

		try {
			javax.swing.text.MaskFormatter cpf = new javax.swing.text.MaskFormatter("###.###.###-##");
			cpfClienteField = new javax.swing.JFormattedTextField(cpf);
		} catch (Exception e) {
		}
		cpfClienteField.setBounds(356, 21, 86, 20);
		panelPessoa.add(cpfClienteField);
		cpfClienteField.setColumns(10);

		rgClienteField = new JTextField();
		rgClienteField.setBounds(57, 49, 223, 20);
		panelPessoa.add(rgClienteField);
		rgClienteField.setColumns(10);

		nascimentoClienteField = new com.toedter.calendar.JDateChooser();
		nascimentoClienteField.setBounds(355, 108, 87, 20);
		panelPessoa.add(nascimentoClienteField);

		JLabel lbltelefone = new JLabel("*Telefone:");
		lbltelefone.setBounds(290, 52, 59, 14);
		panelPessoa.add(lbltelefone);

		JLabel lblE = new JLabel("E-mail:");
		lblE.setBounds(10, 77, 37, 14);
		panelPessoa.add(lblE);

		JLabel lblNewLabel_2 = new JLabel("Sexo:");
		lblNewLabel_2.setBounds(290, 77, 28, 14);
		panelPessoa.add(lblNewLabel_2);

		sexoClienteBox = new JComboBox();
		for (Sexo sexo : Sexo.values()) {
			sexoClienteBox.addItem(sexo);
		}
		// sexoClienteBox.setModel(new DefaultComboBoxModel(new String[] {
		// "Masculino", "Feminino" }));
		// sexoClienteBox.setSelectedIndex(0);
		// sexoClienteBox.setMaximumRowCount(3);
		sexoClienteBox.setBounds(356, 77, 86, 20);
		panelPessoa.add(sexoClienteBox);

		JLabel lblEstadoCivil = new JLabel("Estado Civil:");
		lblEstadoCivil.setBounds(10, 112, 59, 14);
		panelPessoa.add(lblEstadoCivil);

		estadoCivilClienteBox = new JComboBox();
		for (EstadoCivil estadoCivil : EstadoCivil.values()) {
			estadoCivilClienteBox.addItem(estadoCivil);
		}
		// estadoCivilClienteBox.setModel(new DefaultComboBoxModel(new String[]
		// { "Solteiro(a)", "Casado(a)", " Viúvo(a)", " Divorciado(a)" }));
		// estadoCivilClienteBox.setSelectedIndex(0);
		// estadoCivilClienteBox.setMaximumRowCount(3);
		estadoCivilClienteBox.setBounds(77, 109, 92, 20);
		panelPessoa.add(estadoCivilClienteBox);
		try {
			javax.swing.text.MaskFormatter telefone = new javax.swing.text.MaskFormatter("(##)####-####");
			telefoneClienteField = new javax.swing.JFormattedTextField(telefone);
		} catch (Exception e) {
		}

		telefoneClienteField.setBounds(356, 49, 86, 20);
		panelPessoa.add(telefoneClienteField);
		telefoneClienteField.setColumns(10);

		emailClienteField = new JTextField();
		emailClienteField.setBounds(57, 77, 223, 20);
		panelPessoa.add(emailClienteField);
		emailClienteField.setColumns(10);

		panelEndereco = new JPanel();
		panelEndereco.setBorder(new TitledBorder(null, "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelEndereco.setBounds(10, 204, 452, 107);
		panelDados.add(panelEndereco);
		panelEndereco.setLayout(null);

		JLabel lblcidade = new JLabel("*Cidade:");
		lblcidade.setBounds(10, 24, 43, 14);
		panelEndereco.add(lblcidade);

		JLabel lblrua = new JLabel("*Rua:");
		lblrua.setBounds(10, 49, 29, 14);
		panelEndereco.add(lblrua);

		JLabel lblbairro = new JLabel("*Bairro:");
		lblbairro.setBounds(10, 74, 38, 14);
		panelEndereco.add(lblbairro);

		cidadeClienteField = new JTextField();
		cidadeClienteField.setBounds(63, 21, 132, 20);
		panelEndereco.add(cidadeClienteField);
		cidadeClienteField.setColumns(10);

		ruaClienteField = new JTextField();
		ruaClienteField.setBounds(63, 46, 132, 20);
		panelEndereco.add(ruaClienteField);
		ruaClienteField.setColumns(10);

		bairroClienteField = new JTextField();
		bairroClienteField.setBounds(63, 71, 132, 20);
		panelEndereco.add(bairroClienteField);
		bairroClienteField.setColumns(10);

		JLabel lblestado = new JLabel("*Estado:");
		lblestado.setBounds(205, 71, 43, 14);
		panelEndereco.add(lblestado);

		JLabel lblcomplemento = new JLabel("*Complemento:");
		lblcomplemento.setBounds(205, 24, 75, 14);
		panelEndereco.add(lblcomplemento);

		JLabel lblcep = new JLabel("*CEP:");
		lblcep.setBounds(205, 46, 29, 14);
		panelEndereco.add(lblcep);

		estadoClienteBox = new JComboBox();
		estadoClienteBox.setModel(new DefaultComboBoxModel(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
				"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		estadoClienteBox.setSelectedIndex(15);
		estadoClienteBox.setMaximumRowCount(27);
		estadoClienteBox.setBounds(258, 68, 54, 20);
		panelEndereco.add(estadoClienteBox);

		complementoClienteField = new JTextField();
		complementoClienteField.setBounds(290, 21, 152, 20);
		panelEndereco.add(complementoClienteField);
		complementoClienteField.setColumns(10);

		JLabel lblnmero = new JLabel("*Número:");
		lblnmero.setBounds(336, 71, 47, 14);
		panelEndereco.add(lblnmero);

		numeroClienteField = new JTextField();
		numeroClienteField.setBounds(393, 68, 49, 20);
		panelEndereco.add(numeroClienteField);
		numeroClienteField.setColumns(10);

		try {
			javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("#####-###");
			cepClienteField = new javax.swing.JFormattedTextField(cep);
		} catch (Exception e) {
		}
		cepClienteField.setBounds(290, 46, 86, 20);
		panelEndereco.add(cepClienteField);
		cepClienteField.setColumns(10);

		JLabel lblCdigo = new JLabel("Código:");
		lblCdigo.setBounds(10, 11, 45, 16);
		panelDados.add(lblCdigo);

		idClienteField = new JTextField();
		idClienteField.setBounds(65, 9, 53, 20);
		panelDados.add(idClienteField);
		idClienteField.setColumns(10);
		idClienteField.setEnabled(false);

		JLabel lblDataCadastro = new JLabel("Data Cadastro:");
		lblDataCadastro.setBounds(130, 12, 82, 16);
		panelDados.add(lblDataCadastro);
		dataCadastroField = new JTextField();
		dataCadastroField.setBounds(213, 9, 68, 20);
		panelDados.add(dataCadastroField);
		dataCadastroField.setColumns(10);
		dataCadastroField.setEnabled(false);

	}

	public void novo() {
		super.novo();
		idClienteField.setEnabled(false);
		dataCadastroField.setEnabled(false);
	}

	@Override
	public void gravar() {
		try {
			verificaCamposObrigatorios();
		} catch (Exception e) {
			showMessageDialog(null, e.getMessage(), "Campos Faltantes", ERROR_MESSAGE);
		}

		Pessoa cliente = obterCliente();
		try {
			this.clienteService.gravar(cliente);
			showMessageDialog(null, "Pessoa cadastrado com sucesso!", "Cadastro", INFORMATION_MESSAGE);
			super.gravar();
		} catch (BusinessException ex) {
			showMessageDialog(null, ex.getMessage(), "Erro", ERROR_MESSAGE);
			ex.printStackTrace();
		}

	}

	public void cancelar() {
		setComponentsDisable();
		super.cancelar();
	}

	public void localizar() {
		abrirFormBuscaCliente();
		super.localizar();
	}

	public void abrirFormBuscaCliente() {
		//atualizarCliente((Pessoa) new FormBuscaCliente().abrirPesquisa());
	}

	@Override
	public void limparCampos() {
		limpaCampos();
	}

	public void habilitarCampos() {
		super.localizar();
	}

	private void verificaCamposObrigatorios() throws Exception {
		StringBuilder camposFaltantes = new StringBuilder();
		if (nomeClienteField.getText().isEmpty())
			camposFaltantes.append("Nome do Pessoa.");

		if (telefoneClienteField.getText().isEmpty())
			camposFaltantes.append("Telefone.");
		if (cpfClienteField.getText().isEmpty())
			camposFaltantes.append("CPF.");
		if (rgClienteField.getText().isEmpty())
			camposFaltantes.append("RG.");
		if (cidadeClienteField.getText().isEmpty())
			camposFaltantes.append("Cidade.");
		if (ruaClienteField.getText().isEmpty())
			camposFaltantes.append("Rua.");
		if (bairroClienteField.getText().isEmpty())
			camposFaltantes.append("Bairro.");
		if (numeroClienteField.getText().isEmpty())
			camposFaltantes.append("Número.");

		if (camposFaltantes.length() > 0)
			throw new Exception("Campos Obrigatórios faltantes: " + camposFaltantes);

	}

	private void atualizarCliente(Pessoa cliente) {
		ViewMethods.chaveadorCampos(panelEndereco, true);
		ViewMethods.chaveadorCampos(panelPessoa, true);
		/*idClienteField.setText(String.valueOf(cliente.getIdCliente()));
		cpfClienteField.setText(cliente.getCpf());
		telefoneClienteField.setText(cliente.getTelefone());
		nomeClienteField.setText(cliente.getNome());
		rgClienteField.setText(cliente.getRg());
		emailClienteField.setText(cliente.getEmail());
		dataCadastroField.setText(cliente.getDataCadastro());
		cidadeClienteField.setText(cliente.getEndereco().getCidade());
		cepClienteField.setText(cliente.getEndereco().getCep());
		ruaClienteField.setText(cliente.getEndereco().getRua());
		complementoClienteField.setText(cliente.getEndereco().getComplemento());
		numeroClienteField.setText(String.valueOf(cliente.getEndereco().getNumero()));
		sexoClienteBox.setSelectedItem(cliente.getSexo());
		estadoCivilClienteBox.setSelectedItem(cliente.getEstadoCivil());
		estadoClienteBox.setSelectedItem(cliente.getEndereco().getEstado());
		bairroClienteField.setText(cliente.getEndereco().getBairro());
		String formato = "dd/MM/yyyy";
		Date DataTemp = new Date();
		try {
			DataTemp = new SimpleDateFormat(formato).parse(cliente.getDataNascimento());
		} catch (ParseException ex) {
		}
		nascimentoClienteField.setDate(DataTemp);*/
	}

	private Pessoa obterCliente() {
		/*Date data = new Date();
		data = nascimentoClienteField.getDate();
		Pessoa cliente = new Pessoa();
		cliente.setNome(nomeClienteField.getText());
		cliente.setCpf(cpfClienteField.getText());
		cliente.setRg(rgClienteField.getText());
		cliente.setDataNascimento(formatarDate.format(data));
		cliente.setTelefone(telefoneClienteField.getText());
		cliente.setEmail(emailClienteField.getText());
		cliente.setSexo((Sexo) (sexoClienteBox.getSelectedItem()));
		cliente.setEstadoCivil((EstadoCivil) estadoCivilClienteBox.getSelectedItem());
		Endereco endereco = new Endereco();
		endereco.setCidade(cidadeClienteField.getText());
		endereco.setRua(ruaClienteField.getText());
		endereco.setBairro(bairroClienteField.getText());
		endereco.setComplemento(complementoClienteField.getText());
		endereco.setNumero(Integer.parseInt(numeroClienteField.getText()));
		endereco.setCep(cepClienteField.getText());
		endereco.setEstado(estadoClienteBox.getSelectedItem().toString());
		cliente.setEndereco(endereco);
		cliente.setDataCadastro(dataCadastroField.getText());
		if (!idClienteField.getText().equals("")) {
			cliente.setIdCliente(Integer.valueOf(idClienteField.getText()));
		}
		return cliente;
		 */
		return null;
	}

	public void limpaCampos() {
		ViewMethods.limparCampos(panelEndereco);
		ViewMethods.limparCampos(panelPessoa);

	}

	public ClienteService getClienteService() {
		if (clienteService == null) {
			//clienteService = new ServiceLocator().loadService(ClienteService.class);
		}
		return clienteService;
	}

	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public void excluir() {
		Pessoa cliente = obterCliente();
		try {
			this.clienteService.excluir(cliente);
			showMessageDialog(null, "Pessoa excluída com sucesso!", "Cadastro", INFORMATION_MESSAGE);
			super.excluir();
		} catch (BusinessException ex) {
			showMessageDialog(null, ex.getMessage(), "Erro", ERROR_MESSAGE);
		}

	}
}
