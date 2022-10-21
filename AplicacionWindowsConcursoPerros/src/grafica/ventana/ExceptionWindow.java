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
import javax.swing.border.BevelBorder;

public class ExceptionWindow {

	private JFrame errorFrame;

	/**
	 * Launch the application.
	 */
	public static final int ACCION_EXIT = 0;
	public static final int ACCION_DISPOSE = 1;

	public ExceptionWindow(int accion, String message) {
		errorFrame = new JFrame();
		errorFrame.setResizable(false);
		errorFrame.setTitle("Error");
		errorFrame.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(ExceptionWindow.class.getResource("/grafica/imagenes/pngwing.com.png")));
		errorFrame.setBounds(100, 100, 396, 193);
		int closeOperation = accion == ACCION_EXIT ? JFrame.EXIT_ON_CLOSE : JFrame.DISPOSE_ON_CLOSE;
		errorFrame.setDefaultCloseOperation(closeOperation);
		errorFrame.getContentPane().setLayout(null);
		errorFrame.setLocationRelativeTo(null);

		JLabel lblError = new JLabel("Error: ");
		lblError.setFont(new Font("Microsoft Tai Le", Font.BOLD, 28));
		lblError.setBounds(69, 11, 108, 50);
		errorFrame.getContentPane().add(lblError);

		Image errorImage = new ImageIcon(getClass().getResource("/grafica/imagenes/pngwing.com.png")).getImage();
		Image sclError = errorImage.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		ImageIcon errorIcon = new ImageIcon(sclError);

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(errorIcon);
		lblIcon.setBounds(21, 11, 50, 50);
		errorFrame.getContentPane().add(lblIcon);

		JLabel lblErrorMessage = new JLabel(message);
		lblErrorMessage.setBounds(52, 66, 290, 37);
		errorFrame.getContentPane().add(lblErrorMessage);

		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.addActionListener(getAction(accion, errorFrame));

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
		btnNewButton.setBounds(151, 114, 89, 23);
		errorFrame.getContentPane().add(btnNewButton);

		errorFrame.setVisible(true);
	}

	public ActionListener getAction(int action, JFrame frame) {
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (action == 0) {
					System.exit(-1);
				} else {
					frame.dispose();
				}

			}
		};

		return listener;
	}
}
