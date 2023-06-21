package painel;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import validacao.ValidaCPF;

public class PessoaPainel {

	private static final String[] DDD = { "011", "021", "027", "031", "041", "047", "051", "061", "062", "063", "065",
			"067", "068", "069", "071", "079", "081", "082", "083", "084", "085", "086", "091", "092", "095", "096",
			"098" };

	public static String[] novo() {
		String[] pessoa = new String[5];

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));

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
		panel.add(new JLabel("Data de Nascimento: "));
		JDateChooser dateChooser = new JDateChooser();
		panel.add(dateChooser);

		
		panel.getComponent(1).addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e){
				
				String cpf = ((JTextField) panel.getComponent(1)).getText();
				
				if (!ValidaCPF.isCPF(cpf)) {
					JOptionPane.showMessageDialog(null, "CPF Invalido", "Atenção", 2);
					((JTextField) panel.getComponent(1)).grabFocus();
				}
				
			}
		});
		
		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String cpf = ((JTextField) panel.getComponent(1)).getText();
			String nome = ((JTextField) panel.getComponent(3)).getText();
			String sobrenome = ((JTextField) panel.getComponent(5)).getText();
			String ddd = (String) dddComboBox.getSelectedItem();
			String numero = ((JTextField) panel.getComponent(9)).getText();
			Date dataNascimento = ((JDateChooser) panel.getComponent(11)).getDate();
			
			pessoa[0] = cpf;
			pessoa[1] = nome;
			pessoa[2] = sobrenome;
			pessoa[3] = "55" + ddd + numero;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			pessoa[4] = simpleDateFormat.format(dataNascimento);
	
		}

		return pessoa;
	}
	
	
	public static String getCPF() {
		String cpf = "";

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("CPF: "));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			cpf = ((JTextField) panel.getComponent(1)).getText();
		}

		return cpf;
	}

	public static String[] getNome() {
		String[] nomeCompleto = new String[2];

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("Primeiro Nome: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Sobrenome: "));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			nomeCompleto[0] = ((JTextField) panel.getComponent(1)).getText();
			nomeCompleto[1] = ((JTextField) panel.getComponent(3)).getText();
		}

		return nomeCompleto;
	}

	public static String[] getIdade() {
		String[] idades = new String[2];

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("Maior que: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Menor que: "));
		panel.add(new JTextField());

		int result = JOptionPane.showConfirmDialog(null, panel, "Idade é: ", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			idades[0] = ((JTextField) panel.getComponent(1)).getText();
			idades[1] = ((JTextField) panel.getComponent(3)).getText();
		}

		return idades;
	}
}
