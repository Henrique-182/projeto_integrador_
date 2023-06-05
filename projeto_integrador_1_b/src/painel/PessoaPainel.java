package painel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PessoaPainel {
	
	private static final String[] DDD = { "068", "082", "096", "092", "097", "071", "073", "074", "075", "077", "085",
			"088", "061", "027", "028", "062", "064", "098", "099", "065", "066", "067", "031", "032", "033", "034",
			"035", "037", "038", "091", "093", "083", "041", "042", "043", "044", "045", "046", "081", "087", "086",
			"089", "021", "022", "024", "084", "051", "053", "054", "055", "069", "095", "047", "048", "049", "011",
			"012", "013", "014", "015", "016", "017", "018", "019", "079", "063" };

	public static String[] novo() {
		String[] pessoa = { "", "", "", "" };

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		
		panel.add(new JLabel("CPF: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Nome:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Sobrenome :"));
		panel.add(new JTextField());
		panel.add(new JLabel("DDD:"));
		JComboBox<String> dddComboBox = new JComboBox<>(DDD);
		panel.add(dddComboBox);
		panel.add(new JLabel("Telefone:"));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String cpf = ((JTextField) panel.getComponent(1)).getText();
			String nome = ((JTextField) panel.getComponent(3)).getText();
			String sobrenome = ((JTextField) panel.getComponent(5)).getText();
			String ddd = (String) dddComboBox.getSelectedItem();
			String numero = ((JTextField) panel.getComponent(9)).getText();

			pessoa[0] = cpf;
			pessoa[1] = nome;
			pessoa[2] = sobrenome;
			pessoa[3] = "55" + ddd + numero;
		}

		return pessoa;
	}
}
