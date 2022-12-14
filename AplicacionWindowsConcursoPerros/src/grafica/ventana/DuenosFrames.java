package grafica.ventana;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import grafica.auxiliares.Auxiliares;
import grafica.controladores.Controller;
import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.SolicitudException;
import logicaPersistencia.valueObjects.VODueno;

public class DuenosFrames {

	public static JPanel nuevoDueno(Controller controller) {

		JPanel nuevo = new JPanel();
		nuevo.setName("Nueva Dueño");
		nuevo.setBackground(new Color(60, 179, 113));

		nuevo.setLayout(null);

		JTextField textCedula = new JTextField();
		textCedula.setName("cedula");
		textCedula.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		textCedula.setToolTipText("Cedula con formato X.XXX.XXX-X");
		textCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		textCedula.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textCedula.setForeground(Color.LIGHT_GRAY);
		textCedula.setText("...cedula");
		textCedula.setBounds(215, 29, 135, 27);
		nuevo.add(textCedula);
		textCedula.setColumns(10);

		JLabel lblCedula = new JLabel("Cedula");
		lblCedula.setLabelFor(textCedula);
		lblCedula.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setBounds(53, 29, 152, 27);
		nuevo.add(lblCedula);

		JTextField textnombre = new JTextField();
		textnombre.setName("nombre");
		textnombre.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		textnombre.setToolTipText("No debe contener caracteres especiales ni numeros");
		textnombre.setText("...nombre");
		textnombre.setHorizontalAlignment(SwingConstants.RIGHT);
		textnombre.setForeground(Color.LIGHT_GRAY);
		textnombre.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textnombre.setColumns(10);
		textnombre.setBounds(215, 100, 135, 27);
		nuevo.add(textnombre);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setLabelFor(textnombre);
		lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
		lblNombre.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblNombre.setBounds(53, 99, 152, 27);
		nuevo.add(lblNombre);

		JTextField textApellido = new JTextField();
		textApellido.setName("apellido");

		textApellido.addFocusListener(Auxiliares.getTextFieldFocusAdapter());

		textApellido.setToolTipText("No debe contener caracteres especiales ni numeros");
		textApellido.setText("...apellido");
		textApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		textApellido.setForeground(Color.LIGHT_GRAY);
		textApellido.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textApellido.setColumns(10);
		textApellido.setBounds(215, 167, 135, 27);
		nuevo.add(textApellido);

		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setLabelFor(textApellido);
		lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
		lblApellido.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblApellido.setBounds(53, 166, 152, 27);
		nuevo.add(lblApellido);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIngresar.setBackground(Color.LIGHT_GRAY);
		btnIngresar.setBounds(169, 218, 89, 23);
		nuevo.add(btnIngresar);

		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnIngresar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				int cedula;
				String nombre;
				String apellido;
				String[] regex = { "[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]", "[a-zA-Z]+", "[a-zA-Z]+" };
				JTextField[] fields = { textCedula, textApellido, textnombre };

				boolean correcto = Auxiliares.formValido(fields, regex);

				btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

				if (correcto) {
					cedula = Integer.parseInt(textCedula.getText().replaceAll("[^0-9]", ""));
					apellido = textApellido.getText();
					nombre = textnombre.getText();
					try {
						controller.crtNuevoDueno(cedula, nombre, apellido);
						NotificacionExito exito = new NotificacionExito(
								"Dueño " + textCedula.getText() + " insertado correctamente");
					} catch (PersistenciaException | SolicitudException e1) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
					} catch (RemoteException e2) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT,
								"Comunicacion interrumpida");
					}

					Auxiliares.reiniciarFormulario(fields);
				}

			}

		});

		return nuevo;
	}

	public static JPanel borrarDueno(Controller controller) {
		JPanel nuevo = new JPanel();
		nuevo.setName("Borrar Dueño");
		nuevo.setBackground(new Color(60, 179, 113));

		nuevo.setLayout(null);

		JTextField textCedula = new JTextField();
		textCedula.setName("cedula");
		textCedula.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		textCedula.setToolTipText("Cedula con formato X.XXX.XXX-X");
		textCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		textCedula.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textCedula.setForeground(Color.LIGHT_GRAY);
		textCedula.setText("...cedula");
		textCedula.setBounds(216, 44, 135, 27);
		nuevo.add(textCedula);
		textCedula.setColumns(10);

		JLabel lblCedula = new JLabel("Cedula ");
		lblCedula.setLabelFor(textCedula);
		lblCedula.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setBounds(55, 44, 152, 27);
		nuevo.add(lblCedula);

		JButton btnIngresar = new JButton("Borrar");
		btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnIngresar.setBackground(Color.LIGHT_GRAY);
		btnIngresar.setBounds(166, 100, 89, 23);
		nuevo.add(btnIngresar);

		btnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnIngresar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				int cedula;
				String[] regex = { "[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]" };
				JTextField[] fields = { textCedula };

				boolean correcto = Auxiliares.formValido(fields, regex);

				btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

				if (correcto) {
					cedula = Integer.parseInt(textCedula.getText().replaceAll("[^0-9]", ""));

					try {
						controller.crtBorrarDuenoMascota(cedula);
						NotificacionExito exito = new NotificacionExito(
								"Dueño " + textCedula.getText() + " borrado correctamente");
					} catch (PersistenciaException | SolicitudException e1) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
					} catch (RemoteException e2) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT,
								"Comunicacion interrumpida");
					}

					Auxiliares.reiniciarFormulario(fields);
				}

			}

		});
		return nuevo;
	}

	public static JPanel listarDuenos(Controller controller) {
		JPanel nuevo = new JPanel();
		nuevo.setName("Listar Dueños");
		nuevo.setBackground(new Color(60, 179, 113));
		nuevo.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 72, 313, 152);
		nuevo.add(scrollPane);

		List<VODueno> duenos = new ArrayList<VODueno>();

		try {
			duenos = controller.crtListarDuenos();
		} catch (PersistenciaException e1) {
			ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
		} catch (RemoteException e2) {
			ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT, "Comunicacion interrumpida");
		}

		JTable table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 14));

		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Cedula", "Nombre", "Apellido" }) {
			Class[] columnTypes = new Class[] { Integer.class, String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		table.setModel(model);
		for (VODueno dueno : duenos) {
			Object[] dataObjects = { dueno.getCedula(), dueno.getNombre(), dueno.getApellido() };
			model.addRow(dataObjects);
		}
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
		scrollPane.setViewportView(table);

		JLabel lblTitle = new JLabel("Lista de Dueños");
		lblTitle.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(54, 11, 313, 25);
		nuevo.add(lblTitle);

		JButton btnRecargar = new JButton("Recargar");
		btnRecargar.setBackground(Color.LIGHT_GRAY);
		btnRecargar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnRecargar.setBounds(169, 235, 89, 23);
		nuevo.add(btnRecargar);

		btnRecargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnRecargar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnRecargar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

				List<VODueno> duenosActual = new ArrayList<VODueno>();

				try {
					duenosActual = controller.crtListarDuenos();
				} catch (RemoteException e1) {
					ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT,
							"Comunicacion Interrumpida");
				} catch (PersistenciaException e1) {
					ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
				}

				Auxiliares.vaciarTabla(table);

				for (VODueno dueno : duenosActual) {
					Object[] dataObjects = { dueno.getCedula(), dueno.getNombre(), dueno.getApellido() };
					model.addRow(dataObjects);
				}

			}
		});
		return nuevo;
	}

}
