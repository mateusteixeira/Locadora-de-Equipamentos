package com.datacoper.locacaoequipamentos.client.formspadrao;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.*;

import com.datacoper.locacaoequipamentos.client.util.ViewMethods;
import com.datacoper.locacaoequipamentos.common.exception.BusinessException;

public abstract class FormPadraoCadastro<T> extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	protected JPanel panelDados;
	protected JPanel panel;
	private JButton btnNovo;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnLocalizar;
	private JButton btnLimpar;
	private JButton btnExcluir;

	protected T object;

	public FormPadraoCadastro(T object) {
		initComponents();

		this.object = object;
	}

	public abstract void carregarTela(T obj);

	protected void initComponents() {
		setClosable(true);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout(0, 0));

		panelDados = new JPanel();
		getContentPane().add(panelDados, BorderLayout.CENTER);

		panel = new JPanel();
		panel.setBorder(null);
		panel.setPreferredSize(new Dimension(90, 10));
		getContentPane().add(panel, BorderLayout.EAST);

		btnNovo = new JButton("Novo");
		btnNovo.setBounds(2, 16, 87, 23);
		btnNovo.setPreferredSize(new Dimension(87, 23));
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				novo();
			}
		});
		panel.setLayout(null);
		panel.add(btnNovo);

		btnGravar = new JButton("Gravar");
		btnGravar.setBounds(2, 50, 87, 23);
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravar();
			}
		});
		btnGravar.setPreferredSize(new Dimension(87, 23));
		panel.add(btnGravar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(2, 84, 87, 23);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}
		});
		btnCancelar.setPreferredSize(new Dimension(87, 23));
		panel.add(btnCancelar);

		btnLocalizar = new JButton("Localizar");
		btnLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				localizar();
			}
		});
		btnLocalizar.setBounds(2, 118, 87, 23);
		btnLocalizar.setPreferredSize(new Dimension(87, 23));
		panel.add(btnLocalizar);

		btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluir();
			}
		});
		btnExcluir.setBounds(2, 187, 87, 23);
		panel.add(btnExcluir);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});
		btnLimpar.setBounds(2, 152, 87, 23);
		panel.add(btnLimpar);

		btnLimpar.setEnabled(false);
		btnGravar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnCancelar.setEnabled(false);
	}

	public void novo() {
		ViewMethods.chaveadorCampos(this.panelDados, true);
		ViewMethods.limparCampos(panelDados);
		btnCancelar.setEnabled(true);
		btnGravar.setEnabled(true);
		btnLimpar.setEnabled(true);
		btnLocalizar.setEnabled(false);
		btnExcluir.setEnabled(false);
		btnNovo.setEnabled(false);

	}

	public void gravar() {
		ViewMethods.limparCampos(panelDados);
		ViewMethods.chaveadorCampos(this.panelDados, false);
		
		btnGravar.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnLocalizar.setEnabled(true);
		btnNovo.setEnabled(true);
		btnExcluir.setEnabled(false);
	}

	public void cancelar() {
		ViewMethods.limparCampos(panelDados);
		ViewMethods.chaveadorCampos(this.panelDados, false);
		btnGravar.setEnabled(false);
		btnLimpar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnNovo.setEnabled(true);
		btnLocalizar.setEnabled(true);
		btnExcluir.setEnabled(false);
	}

	public void localizar() {
		btnGravar.setEnabled(true);
		btnExcluir.setEnabled(true);
		btnCancelar.setEnabled(true);
		btnNovo.setEnabled(false);
	}

	public abstract void limparCampos();

	public void excluir() {
		ViewMethods.limparCampos(panelDados);
		ViewMethods.chaveadorCampos(this.panelDados, false);
		btnNovo.setEnabled(true);
		btnGravar.setEnabled(false);
		btnCancelar.setEnabled(false);
		btnLocalizar.setEnabled(true);
		btnLimpar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}

	public abstract void verificarCamposObrigatorios(T objeto) throws BusinessException;
}
