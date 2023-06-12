package painel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelefoneCentroPainel {

	private static final String[] DDD = { "011", "021", "027", "031", "041", "047", "051", "061", "062", "063", "065",
			"067", "068", "069", "071", "079", "081", "082", "083", "084", "085", "086", "091", "092", "095", "096",
			"098" };

	public static String[][] novo() {
		String[][] telefones = new String[2][2];

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 8));

		panel.add(new JLabel("Primeiro DDD: "));
		JComboBox<String> dddComboBox = new JComboBox<>(DDD);
		panel.add(dddComboBox);
		panel.add(new JLabel("Primeiro Número: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Segundo DDD: "));
		JComboBox<String> dddComboBox2 = new JComboBox<>(DDD);
		panel.add(dddComboBox2);
		panel.add(new JLabel("Segundo Número: "));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String primeiroDDD = (String) dddComboBox.getSelectedItem();
			String primeiroNumero = ((JTextField) panel.getComponent(3)).getText();
			String segundoDDD = (String) dddComboBox2.getSelectedItem();
			String segundoNumero = ((JTextField) panel.getComponent(7)).getText();

			telefones[0][0] = primeiroDDD;
			telefones[0][1] = primeiroNumero;
			telefones[1][0] = segundoDDD;
			telefones[1][1] = segundoNumero;
		}

		return telefones;
	}
}
