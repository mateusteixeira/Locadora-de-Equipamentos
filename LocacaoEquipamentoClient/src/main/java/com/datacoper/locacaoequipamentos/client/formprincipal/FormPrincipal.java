package com.datacoper.locacaoequipamentos.client.formprincipal;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.datacoper.locacaoequipamentos.client.cliente.FormCadastroCliente;
import com.datacoper.locacaoequipamentos.client.formspadrao.FormPadraoPesquisa;
import com.datacoper.locacaoequipamentos.common.model.Pessoa;

public class FormPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPrincipal frame = new FormPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormPrincipal() {
		initComponents();
	}
	
	public void initComponents() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(null, "NÃ£o foi possÃ­vel carregar o \"Skin\" padrÃ£o. Definindo o padrÃ£o original.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 484);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Cadastros");
		menuBar.add(mnNewMenu);

		contentPane = new JPanel();
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		desktopPane = new JDesktopPane();
		desktopPane.setBorder(null);
		desktopPane.setBackground(SystemColor.control);
		contentPane.add(desktopPane);

		JMenuItem mntmNewMenuItem = new JMenuItem("Clientes");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JInternalFrame ji = new FormCadastroCliente();
				ji.setVisible(false);
				desktopPane.add(ji);

				try {
					ji.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ji.setVisible(true);

			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnConsultas = new JMenu("Consultas");
		menuBar.add(mnConsultas);

		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abreTelaPesquisa(new FormPadraoPesquisa<Pessoa>(Pessoa.class));
			}
		});
		mnConsultas.add(mntmClientes);
	}
	
	private void abreTela(JInternalFrame ji) {
		((FormCadastroCliente) ji).abrirFormBuscaCliente();
		((FormCadastroCliente) ji).habilitarCampos();
		ji.setVisible(false);
		desktopPane.add(ji);
	}
	
	private <T> void abreTelaPesquisa(FormPadraoPesquisa<T> form) {
		form.abrirPesquisa();
		form.setVisible(true);
	}
}
