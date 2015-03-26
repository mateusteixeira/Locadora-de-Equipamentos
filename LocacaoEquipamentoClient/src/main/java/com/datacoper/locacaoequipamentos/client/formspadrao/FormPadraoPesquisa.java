package com.datacoper.locacaoequipamentos.client.formspadrao;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.datacoper.locacaoequipamentos.client.tablemodel.MyModelTablePesquisa;
import com.datacoper.locacaoequipamentos.common.annotation.ColumnTableSearch;
import com.datacoper.locacaoequipamentos.common.exception.BusinessException;
import com.datacoper.locacaoequipamentos.common.service.ServiceLocator;
import com.datacoper.locacaoequipamentos.common.service.interfaces.PesquisaService;

public class FormPadraoPesquisa<T> extends JDialog {

	private PesquisaService service;
	private String[] filtros;
	private Class<T> classPesquisa;

	/**
	 * @wbp.parser.constructor
	 */
	public FormPadraoPesquisa(Class<T> classPesquisa) {
//		try {
//			Class<?> servico = Class.forName("com.datacoper.locacaoequipamentos.business.servicesimpl.PesquisaServiceImpl");
//			servico.getConstructor(Class.class).newInstance(classPesquisa);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}

		 this(ServiceLocator.loadService(PesquisaService.class, new Class[]{Class.class}, new Object[]{classPesquisa}), classPesquisa);
	}

	public FormPadraoPesquisa(PesquisaService service, Class<T> classPesquisa) {
		this.classPesquisa = classPesquisa;
		this.service = service;

		initComponents();
	}

	private void initComponents() {
		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panelFundo = new JPanel();
		panelFundo.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panelFundo, BorderLayout.CENTER);
		panelFundo.setLayout(new BorderLayout(0, 0));

		JPanel panelCampos = new JPanel();
		panelCampos.setBorder(new TitledBorder(null, "Filtro de Pesquisa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFundo.add(panelCampos, BorderLayout.NORTH);
		GridBagLayout gbl_panelCampos = new GridBagLayout();
		gbl_panelCampos.columnWidths = new int[] { 82, 481 };
		gbl_panelCampos.rowHeights = new int[] { 28, 20 };
		gbl_panelCampos.columnWeights = new double[] { 0.0, 1.0 };
		gbl_panelCampos.rowWeights = new double[] { 0.0, 0.0 };
		panelCampos.setLayout(gbl_panelCampos);

		JLabel lblPesquisarPor = new JLabel("Pesquisar por:");
		lblPesquisarPor.setPreferredSize(new Dimension(80, 14));
		lblPesquisarPor.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_lblPesquisarPor = new GridBagConstraints();
		gbc_lblPesquisarPor.anchor = GridBagConstraints.EAST;
		gbc_lblPesquisarPor.ipady = 2;
		gbc_lblPesquisarPor.ipadx = 2;
		gbc_lblPesquisarPor.fill = GridBagConstraints.VERTICAL;
		gbc_lblPesquisarPor.insets = new Insets(0, 0, 5, 5);
		gbc_lblPesquisarPor.gridx = 0;
		gbc_lblPesquisarPor.gridy = 0;
		panelCampos.add(lblPesquisarPor, gbc_lblPesquisarPor);

		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(200, 20));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.fill = GridBagConstraints.VERTICAL;
		gbc_comboBox.anchor = GridBagConstraints.WEST;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 0;
		panelCampos.add(comboBox, gbc_comboBox);
		comboBox.setModel(getFiltersModel());

		FieldPesquisa = new JTextField();

		GridBagConstraints gbc_FieldPesquisa = new GridBagConstraints();
		gbc_FieldPesquisa.gridwidth = 2;
		gbc_FieldPesquisa.fill = GridBagConstraints.BOTH;
		gbc_FieldPesquisa.gridx = 0;
		gbc_FieldPesquisa.gridy = 1;
		panelCampos.add(FieldPesquisa, gbc_FieldPesquisa);
		FieldPesquisa.setColumns(2);

		JPanel panelButtons = new JPanel();
		panelFundo.add(panelButtons, BorderLayout.SOUTH);
		FlowLayout flowLayout = (FlowLayout) panelButtons.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);

		btnOk = new JButton("Ok");
		btnOk.setPreferredSize(new Dimension(75, 23));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ok();
			}
		});

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		panelButtons.add(btnCancelar);
		panelButtons.add(btnOk);

		JPanel panelTable = new JPanel();
		panelFundo.add(panelTable, BorderLayout.CENTER);
		panelTable.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Pesquisa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTable.add(scrollPane);

		table = new JTable(new MyModelTablePesquisa<T>(classPesquisa, pesquisar()));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					dispose();
				}
			}
		});
		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		table.setRowSorter(sorter);
		// List<RowSorter.SortKey> sortKeys = new
		// ArrayList<RowSorter.SortKey>();
		// sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
		// sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
		// sorter.setSortKeys(sortKeys);

		scrollPane.setViewportView(table);

		FieldPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String pesquisa = FieldPesquisa.getText();

				// table.setModel((getTableModel(pesquisar(pesquisa))));
				table.revalidate();
				table.repaint();
			}
		});
		setSize(820, 460);
		setResizable(false);
		setLocationRelativeTo(null);
		setModal(true);
	}

	private ComboBoxModel<List<Filtros>> getFiltersModel() {
		List<Filtros> filtros = new LinkedList<>();

		for (Field f : classPesquisa.getDeclaredFields()) {
			if (f.isAnnotationPresent(ColumnTableSearch.class)) {
				ColumnTableSearch columnTableSearch = f.getAnnotation(ColumnTableSearch.class);
				if (columnTableSearch.isFilter()) {
					String header = columnTableSearch.FilterTitle();
					header = header.equals("") ? columnTableSearch.header() : header;
					filtros.add(new Filtros(header, f.getName()));
				}
			}
		}

		return new DefaultComboBoxModel(filtros.toArray());
	}

	private static final long serialVersionUID = 1L;
	private JButton btnOk;
	private JButton btnCancelar;
	private JTextField FieldPesquisa;
	private JTable table;
	private ButtonGroup buttonGroup;
	private JComboBox comboBox;

	public void ok() {
		dispose();
	}

	public void cancelar() {
		dispose();
	}

	public T getSelectedRow(int selectedRow) {
		return ((MyModelTablePesquisa<T>) table.getModel()).get(selectedRow);
	}

	// public abstract TableModel getTableModel(List lista);

	public List<T> pesquisar() {
		try {
			return service.pesquisar(comboBox.getSelectedItem().toString(), FieldPesquisa.getText());
		} catch (BusinessException e) {
			showMessageDialog(null, "Erro", e.getMessage(), ERROR_MESSAGE);
		}
		return null;
	}

	public T abrirPesquisa() {
		setVisible(true);
		return getSelectedRow(table.getSelectedRow());
	}
	
	public class Filtros {
		private String header;
		private String coluna;
		
		public Filtros(String header, String coluna) {
			super();
			this.header = header;
			this.coluna = coluna;
		}

		public String getHeader() {
			return header;
		}
		
		public void setHeader(String header) {
			this.header = header;
		}
		
		public String getColuna() {
			return coluna;
		}
		
		public void setColuna(String coluna) {
			this.coluna = coluna;
		}
		
		@Override
		public String toString() {
			return header;
		}
	}
}
