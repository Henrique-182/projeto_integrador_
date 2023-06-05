package painel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CentroPainel {
	
	private static final String[] TIPO = {"PRIVADO", "PUBLICO"};

	public static String[] novo() {
		String[] centro = { "", "", "", "" };

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("Nome: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Tipo:"));
		JComboBox<String> dddComboBox = new JComboBox<>(TIPO);
		panel.add(dddComboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String nome = ((JTextField) panel.getComponent(1)).getText();
			String tipo = (String) dddComboBox.getSelectedItem();

			centro[0] = nome;
			centro[1] = tipo;
		}

		return centro;
	}
}
