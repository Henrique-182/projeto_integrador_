package painel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EnderecoPainel {

	private final static String[] ESTADOS = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
			"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };


	public static String[] novo() {
		String[] endereco = {"", "", "", "", "", ""};
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("Estado:"));
		JComboBox<String> estadoComboBox = new JComboBox<>(ESTADOS);
		panel.add(estadoComboBox);
		panel.add(new JLabel("Cidade:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Bairro:"));
		panel.add(new JTextField());
		panel.add(new JLabel("CEP:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Logradouro:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Complemento:"));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String estado = (String) estadoComboBox.getSelectedItem();
			String cidade = ((JTextField) panel.getComponent(3)).getText();
			String bairro = ((JTextField) panel.getComponent(5)).getText();
			String cep = ((JTextField) panel.getComponent(7)).getText();
			String logradouro = ((JTextField) panel.getComponent(9)).getText();
			String complemento = ((JTextField) panel.getComponent(11)).getText();

			endereco[0] = estado;
			endereco[1] = cidade;
			endereco[2] = bairro;
			endereco[3] = cep;
			endereco[4] = logradouro;
			endereco[5] = complemento;
		}
		
		return endereco;
	}

	public static String getEstado() {
		String estado = "";
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2));

		panel.add(new JLabel("Estado:"));
		JComboBox<String> estadoComboBox = new JComboBox<>(ESTADOS);
		panel.add(estadoComboBox);

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			estado = (String) estadoComboBox.getSelectedItem();
		}
		
		return estado;
	}
}
