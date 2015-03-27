package com.datacoper.locacaoequipamentos.client.cliente;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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
import com.datacoper.locacaoequipamentos.common.model.Cidade;
import com.datacoper.locacaoequipamentos.common.model.Endereco;
import com.datacoper.locacaoequipamentos.common.model.Estado;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;
import com.datacoper.locacaoequipamentos.common.model.RelacaoPessoa;
import com.datacoper.locacaoequipamentos.common.model.enums.EstadoCivil;
import com.datacoper.locacaoequipamentos.common.model.enums.Sexo;
import com.datacoper.locacaoequipamentos.common.service.ServiceLocator;
import com.datacoper.locacaoequipamentos.common.service.interfaces.ClienteService;

import java.beans.VetoableChangeListener;
import java.beans.PropertyChangeEvent;

public class FormCadastroCliente extends FormPadraoCadastro<Pessoa> {

	private static final long serialVersionUID = 1L;

	private JTextField idClienteField;
	private JTextField dataCadastroField;
	private JTextField nomeClienteField;
	private JTextField cpfClienteField;
	private JTextField rgClienteField;
	private static com.toedter.calendar.JDateChooser nascimentoClienteField;
	private JTextField telefoneClienteField;
	private JTextField emailClienteField;
	private JTextField ruaClienteField;
	private JTextField bairroClienteField;
	private JTextField complementoClienteField;
	private JTextField numeroClienteField;
	private JTextField cepClienteField;
	private JComboBox sexoClienteBox;
	private JComboBox estadoCivilClienteBox;
	private JComboBox<Estado> estadoClienteBox;

	private SimpleDateFormat formatarDate = new SimpleDateFormat("dd/MM/yyyy");
	private Date date = new Date(System.currentTimeMillis());
	private ClienteService clienteService;
	private JPanel panelPessoa;
	private JPanel panelEndereco;
	private JComboBox<Cidade> boxCidade;

	public FormCadastroCliente() {
		this(null);
	}

	public FormCadastroCliente(Pessoa pessoa) {
		super(pessoa);

		panelDados.setLayout(null);

		getClienteService();
		init();

		setComponentsDisable();

		if (object != null) {
			carregarTela(object);
		}
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
		panelEndereco.setBounds(10, 205, 452, 139);
		panelDados.add(panelEndereco);
		panelEndereco.setLayout(null);

		JLabel lblcidade = new JLabel("*Cidade:");
		lblcidade.setBounds(205, 24, 43, 14);
		panelEndereco.add(lblcidade);

		JLabel lblrua = new JLabel("*Rua:");
		lblrua.setBounds(10, 52, 29, 14);
		panelEndereco.add(lblrua);

		JLabel lblbairro = new JLabel("*Bairro:");
		lblbairro.setBounds(10, 80, 38, 14);
		panelEndereco.add(lblbairro);

		ruaClienteField = new JTextField();
		ruaClienteField.setBounds(63, 49, 379, 20);
		panelEndereco.add(ruaClienteField);
		ruaClienteField.setColumns(10);

		bairroClienteField = new JTextField();
		bairroClienteField.setBounds(63, 77, 132, 20);
		panelEndereco.add(bairroClienteField);
		bairroClienteField.setColumns(10);

		JLabel lblestado = new JLabel("*Estado:");
		lblestado.setBounds(10, 24, 43, 14);
		panelEndereco.add(lblestado);

		JLabel lblcomplemento = new JLabel("*Complemento:");
		lblcomplemento.setBounds(205, 77, 75, 14);
		panelEndereco.add(lblcomplemento);

		JLabel lblcep = new JLabel("*CEP:");
		lblcep.setBounds(205, 111, 29, 14);
		panelEndereco.add(lblcep);

		estadoClienteBox = new JComboBox();
		estadoClienteBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Object[] cidades = clienteService.getCidades((Estado) estadoClienteBox.getSelectedItem()).toArray();
					boxCidade.setModel(new DefaultComboBoxModel(cidades));
					boxCidade.revalidate();
					boxCidade.repaint();
				} catch (BusinessException e1) {
					e1.printStackTrace();
					showMessageDialog(null, e1.getMessage(), "Erro", ERROR_MESSAGE);
				}
			}
		});

		try {
			estadoClienteBox.setModel(new DefaultComboBoxModel(clienteService.getEstados().toArray()));
		} catch (BusinessException e1) {
			showMessageDialog(null, e1.getMessage(), "Erro", ERROR_MESSAGE);
		}
		estadoClienteBox.setBounds(63, 21, 132, 20);
		panelEndereco.add(estadoClienteBox);

		complementoClienteField = new JTextField();
		complementoClienteField.setBounds(290, 74, 152, 20);
		panelEndereco.add(complementoClienteField);
		complementoClienteField.setColumns(10);

		JLabel lblnmero = new JLabel("*Número:");
		lblnmero.setBounds(10, 111, 47, 14);
		panelEndereco.add(lblnmero);

		numeroClienteField = new JTextField();
		numeroClienteField.setBounds(63, 108, 132, 20);
		panelEndereco.add(numeroClienteField);
		numeroClienteField.setColumns(10);

		try {
			javax.swing.text.MaskFormatter cep = new javax.swing.text.MaskFormatter("#####-###");
			cepClienteField = new javax.swing.JFormattedTextField(cep);
		} catch (Exception e) {
		}
		cepClienteField.setBounds(290, 108, 86, 20);
		panelEndereco.add(cepClienteField);
		cepClienteField.setColumns(10);

		boxCidade = new JComboBox();
		boxCidade.setBounds(258, 21, 184, 20);
		panelEndereco.add(boxCidade);

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
		object = obterCliente();

		try {

			verificarCamposObrigatorios(object);
			this.clienteService.gravar(object);
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
		super.localizar();
	}

	@Override
	public void limparCampos() {
		limpaCampos();
	}

	public void habilitarCampos() {
		super.localizar();
	}

	private Pessoa obterCliente() {

		Pessoa pessoa = new Pessoa();
		pessoa.setNmPessoa(nomeClienteField.getText());
		pessoa.setNrCpf(cpfClienteField.getText().replaceAll(".", "").replaceAll("-", ""));
		pessoa.setNrRg(rgClienteField.getText());
		pessoa.setDtNascimento(nascimentoClienteField.getDate());
		pessoa.setNrTelefone(telefoneClienteField.getText().replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("-", ""));
		pessoa.setDsEmail(emailClienteField.getText());
		pessoa.setSexo((Sexo) (sexoClienteBox.getSelectedItem()));
		pessoa.setIdEstadoCivil((EstadoCivil) estadoCivilClienteBox.getSelectedItem());
		pessoa.setRelacaoPessoa(new RelacaoPessoa(1));
		Endereco endereco = new Endereco();
		endereco.setCidade((Cidade) boxCidade.getSelectedItem());
		endereco.setNmLogradouro(ruaClienteField.getText());
		endereco.setNmBairro(bairroClienteField.getText());
		endereco.setDsComplemento(complementoClienteField.getText());
		endereco.setNrEndereco((numeroClienteField.getText()));
		endereco.setNrCep(cepClienteField.getText().replaceAll(".", "").replaceAll("-", ""));
		pessoa.addEndereco(endereco);

		return pessoa;
	}

	public void limpaCampos() {
		ViewMethods.limparCampos(panelEndereco);
		ViewMethods.limparCampos(panelPessoa);

	}

	public ClienteService getClienteService() {
		if (clienteService == null) {
			clienteService = ServiceLocator.loadService(ClienteService.class);
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

	@Override
	public void carregarTela(Pessoa pessoa) {
		ViewMethods.chaveadorCampos(panelEndereco, true);
		ViewMethods.chaveadorCampos(panelPessoa, true);

		idClienteField.setText(String.valueOf(pessoa.getIdPessoa()));
		cpfClienteField.setText(pessoa.getNrCpf());
		telefoneClienteField.setText(pessoa.getNrTelefone());
		nomeClienteField.setText(pessoa.getNmPessoa());
		rgClienteField.setText(pessoa.getNrRg());
		emailClienteField.setText(pessoa.getDsEmail());
		sexoClienteBox.setSelectedItem(pessoa.getSexo());
		estadoCivilClienteBox.setSelectedItem(pessoa.getIdEstadoCivil());
		nascimentoClienteField.setDate(pessoa.getDtNascimento());

		if (pessoa.getDtCadastro() != null) {
			dataCadastroField.setText(DateFormat.getDateInstance().format(pessoa.getDtCadastro()));
		}

		estadoClienteBox.setSelectedItem(pessoa.getEnderecos().get(0).getCidade().getCdEstado());
		boxCidade.setSelectedItem(pessoa.getEnderecos().get(0).getCidade());
		cepClienteField.setText(pessoa.getEnderecos().get(0).getNrCep());
		ruaClienteField.setText(pessoa.getEnderecos().get(0).getNmLogradouro());
		complementoClienteField.setText(pessoa.getEnderecos().get(0).getDsComplemento());
		numeroClienteField.setText(String.valueOf(pessoa.getEnderecos().get(0).getNrEndereco()));
		bairroClienteField.setText(pessoa.getEnderecos().get(0).getNmBairro());

	}

	@Override
	public void verificarCamposObrigatorios(Pessoa pessoa) throws BusinessException {

		StringBuilder builder = new StringBuilder();

		if (pessoa.getNmPessoa().isEmpty()) {
			builder.append("\nNome.");
		}
		if (pessoa.getNrCpf().isEmpty()) {
			builder.append("\nCpf.");
		}
		if (pessoa.getNrTelefone().isEmpty()) {
			builder.append("\nTelefone.");
		}

		if (builder.length() > 0) {
			throw new BusinessException("Seguintes campos obritatórios não preenchidos:" + builder.toString());
		}
	}
}
