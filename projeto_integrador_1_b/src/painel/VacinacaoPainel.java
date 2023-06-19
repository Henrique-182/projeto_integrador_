package painel;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public class VacinacaoPainel {

	public static String[] novo() {
		String[] vacinacao = new String[4];

		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(7, 2));

		panel.add(new JLabel("Id do vacina:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Id da pessoa: "));
		panel.add(new JTextField());
		panel.add(new JLabel("Id do centro:"));
		panel.add(new JTextField());
		panel.add(new JLabel("Data da primeira dose: "));
		JDateChooser dateChooser = new JDateChooser();
		panel.add(dateChooser);

		int result = JOptionPane.showConfirmDialog(null, panel, "Preencha os dados", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {
			String idVacina = ((JTextField) panel.getComponent(1)).getText();
			String idPessoa = ((JTextField) panel.getComponent(3)).getText();
			String idCentro = ((JTextField) panel.getComponent(5)).getText();
			Date dataNascimento = ((JDateChooser) panel.getComponent(7)).getDate();

			vacinacao[0] = idVacina;
			vacinacao[1] = idPessoa;
			vacinacao[2] = idCentro;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			vacinacao[3] = simpleDateFormat.format(dataNascimento);
		}
		
		return vacinacao;
	}

}
