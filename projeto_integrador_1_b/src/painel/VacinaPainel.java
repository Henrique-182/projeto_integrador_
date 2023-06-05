package painel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VacinaPainel {
	
	private static final String[] COMBATE = {"COVID-19"};
	
	public static String[] novo() {
		String[] vacina = {"", "", "", "", ""};

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		
		panel.add(new JLabel("Nome: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Fabricante:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Combate:"));
		JComboBox<String> dddComboBox = new JComboBox<>(COMBATE);
		panel.add(dddComboBox);
		panel.add(new JLabel("Doses mínimas:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Dias para a próxima dose:"));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String nome = ((JTextField) panel.getComponent(1)).getText();
			String fabricante = ((JTextField) panel.getComponent(3)).getText();
			String combate = (String) dddComboBox.getSelectedItem();
			String dosesMinimas = ((JTextField) panel.getComponent(7)).getText();
			String diasProximaDose = ((JTextField) panel.getComponent(9)).getText();

			vacina[0] = nome;
			vacina[1] = fabricante;
			vacina[2] = combate;
			vacina[3] = dosesMinimas;
			vacina[4] = diasProximaDose;
		}

		return vacina;
	}
}
