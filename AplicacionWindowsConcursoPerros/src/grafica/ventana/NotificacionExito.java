package grafica.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class NotificacionExito {

	private JFrame frmExito;

	public NotificacionExito(String message) {
		initialize(message);
	}

	private void initialize(String message) {
		frmExito = new JFrame();
		frmExito.setResizable(false);
		frmExito.setTitle("Exito");
		frmExito.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(NotificacionExito.class.getResource("/grafica/imagenes/dogIcon.png")));
		frmExito.setBounds(100, 100, 360, 176);
		frmExito.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmExito.getContentPane().setLayout(null);
		frmExito.setLocationRelativeTo(null);

		JLabel lblError = new JLabel("Exito");
		lblError.setForeground(new Color(0, 128, 0));
		lblError.setFont(new Font("Microsoft Tai Le", Font.BOLD, 28));
		lblError.setBounds(61, 11, 108, 50);
		frmExito.getContentPane().add(lblError);

		Image errorImage = new ImageIcon(getClass().getResource("/grafica/imagenes/dogIcon.png")).getImage();
		Image sclError = errorImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon errorIcon = new ImageIcon(sclError);

		JLabel lblIcon = new JLabel("");
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);
		lblIcon.setIcon(errorIcon);
		lblIcon.setBounds(10, 11, 324, 50);
		frmExito.getContentPane().add(lblIcon);

		JLabel lblErrorMessage = new JLabel(message);
		lblErrorMessage.setBounds(31, 55, 290, 37);
		frmExito.getContentPane().add(lblErrorMessage);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frmExito.dispose();

			}
		});

		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnNewButton.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));

			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			}
		});
		btnNewButton.setMnemonic('A');
		btnNewButton.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(118, 103, 89, 23);
		frmExito.getContentPane().add(btnNewButton);

		frmExito.setVisible(true);
	}

}
