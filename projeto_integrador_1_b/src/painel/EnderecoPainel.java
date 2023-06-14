package painel;

import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import API.Endereco;
import API.ServicoApiCep;

public class EnderecoPainel {

	private final static String[] ESTADOS = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB",
			"PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };


	public static String[] novo() {
		String[] endereco = new String[6];
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(6, 2));

		panel.add(new JLabel("CEP:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Estado:"));
		JComboBox<String> estadoComboBox = new JComboBox<>(ESTADOS);
		panel.add(estadoComboBox);
		panel.add(new JLabel("Cidade:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Bairro:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Logradouro:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Complemento:"));
		panel.add(new JTextField());
		
		
		
		panel.getComponent(1).addFocusListener(new java.awt.event.FocusAdapter() {
			public void focusLost(java.awt.event.FocusEvent e){

				String cep = ((JTextField) panel.getComponent(1)).getText();
			
			try {
				Endereco endereco = ServicoApiCep.buscaEnderecoPelo(cep);
				
				estadoComboBox.setSelectedItem(endereco.getUf());
				((JTextField) panel.getComponent(5)).setText(endereco.getLocalidade());
				((JTextField) panel.getComponent(7)).setText(endereco.getBairro());
				((JTextField) panel.getComponent(9)).setText(endereco.getLogradouro());
				((JTextField) panel.getComponent(11)).setText(endereco.getComplemento());
				 
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, "CEP NÃO ENCONTRADO", "Atenção", 2);
			}
		
			}
		});
			

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			endereco[0] = (String) estadoComboBox.getSelectedItem();
			endereco[1] = ((JTextField) panel.getComponent(5)).getText();
			endereco[2] = ((JTextField) panel.getComponent(7)).getText();
			endereco[3] = ((JTextField) panel.getComponent(1)).getText();
			endereco[4] = ((JTextField) panel.getComponent(9)).getText();
			endereco[5] = ((JTextField) panel.getComponent(11)).getText();
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
