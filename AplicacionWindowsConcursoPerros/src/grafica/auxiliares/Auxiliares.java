package grafica.auxiliares;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Auxiliares {

	public static FocusAdapter getTextFieldFocusAdapter() {

		FocusAdapter faTextFields = new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				JTextField text = (JTextField) e.getComponent();
				text.setText("");
				text.setForeground(Color.BLACK);
				text.setFont(new Font("Microsoft Tai Le", 0, 14));

			}

			@Override
			public void focusLost(FocusEvent e) {
				JTextField text = (JTextField) e.getComponent();

				if (text.getText().length() < 1) {
					text.setText("..." + e.getComponent().getName());
					text.setForeground(Color.LIGHT_GRAY);
				}
			}
		};

		return faTextFields;
	}

	public static boolean formValido(JTextField[] fields, String[] regex) {

		boolean resp = true;

		for (int i = 0; i < fields.length; i++) {
			if (!fields[i].getText().matches(regex[i])) {

				resp = false;
				fields[i].setForeground(Color.RED);
			}
		}

		return resp;
	}

	public static void reiniciarFormulario(JTextField[] fields) {
		for (JTextField field : fields) {
			field.setText("..." + field.getName());
			field.setForeground(Color.LIGHT_GRAY);
			field.setFont(null);
			field.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		}
	}

	public static void vaciarTabla(JTable table) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int filas = model.getRowCount();

		for (int i = filas - 1; i >= 0; i--) {
			model.removeRow(i);
		}

	}

}
