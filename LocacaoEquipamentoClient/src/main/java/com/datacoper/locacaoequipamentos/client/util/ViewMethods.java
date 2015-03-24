package com.datacoper.locacaoequipamentos.client.util;

import java.awt.Component;
import java.awt.Panel;

import javax.net.ssl.SSLEngineResult.Status;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

import com.datacoper.locacaoequipamentos.client.cliente.FormCadastroCliente;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public abstract class ViewMethods {

	public static void chaveadorCampos(Component component, boolean status) {
		percorrerComponentes(component, status, false);
	}

	public static void limparCampos(Component component) {
		percorrerComponentes(component, false, true);
	}

	private static void percorrerComponentes(Component component, boolean status, boolean clear) {
		if (component instanceof JTextComponent || component instanceof JCalendar || component instanceof JComboBox || component instanceof JButton
				|| component instanceof JRadioButton) {
			if (!clear) {
				component.setEnabled(status);
			} else {
				if (component instanceof JTextComponent) {
					((JTextComponent) component).setText("");
				} else if (component instanceof JComboBox) {
					((JComboBox) component).setSelectedIndex(0);
				} else if (component instanceof JDateChooser) {
					((JDateChooser) component).setDate(null);
				}
			}

		} else if (component instanceof JFrame) {
			for (Component comps : ((JFrame) component).getComponents()) {
				percorrerComponentes(comps, status, clear);
			}
		} else if (component instanceof JDialog) {
			for (Component comps : ((JDialog) component).getComponents()) {
				percorrerComponentes(comps, status, clear);
			}
		} else if (component instanceof JPanel || component instanceof JDesktopPane || component instanceof JInternalFrame || component instanceof JRootPane
				|| component instanceof JLayeredPane) {
			for (Component comps : ((JComponent) component).getComponents()) {
				percorrerComponentes(comps, status, clear);
			}
		}
	}
}
