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
import logicaPersistencia.valueObjects.VOMascota;
import logicaPersistencia.valueObjects.VOMascotaList;

public class MascotasFrames {

	public static JPanel nuevaMascota(Controller controller) {

		JPanel nuevo = new JPanel();
		nuevo.setName("Nueva Mascota");
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

		JLabel lblCedula = new JLabel("Cedula del dueño");
		lblCedula.setLabelFor(textCedula);
		lblCedula.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setBounds(53, 29, 152, 27);
		nuevo.add(lblCedula);

		JTextField textApodo = new JTextField();
		textApodo.setName("apodo");
		textApodo.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		textApodo.setToolTipText("No debe contener caracteres especiales ni numeros");
		textApodo.setText("...apodo");
		textApodo.setHorizontalAlignment(SwingConstants.RIGHT);
		textApodo.setForeground(Color.LIGHT_GRAY);
		textApodo.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textApodo.setColumns(10);
		textApodo.setBounds(215, 100, 135, 27);
		nuevo.add(textApodo);

		JLabel lblApodo = new JLabel("Apodo");
		lblApodo.setLabelFor(textApodo);
		lblApodo.setHorizontalAlignment(SwingConstants.CENTER);
		lblApodo.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblApodo.setBounds(53, 99, 152, 27);
		nuevo.add(lblApodo);

		JTextField textRaza = new JTextField();
		textRaza.setName("raza");

		textRaza.addFocusListener(Auxiliares.getTextFieldFocusAdapter());

		textRaza.setToolTipText("No debe contener caracteres especiales ni numeros");
		textRaza.setText("...raza");
		textRaza.setHorizontalAlignment(SwingConstants.RIGHT);
		textRaza.setForeground(Color.LIGHT_GRAY);
		textRaza.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textRaza.setColumns(10);
		textRaza.setBounds(215, 167, 135, 27);
		nuevo.add(textRaza);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setLabelFor(textRaza);
		lblRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaza.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblRaza.setBounds(53, 166, 152, 27);
		nuevo.add(lblRaza);

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
				String raza;
				String apodo;
				String[] regex = { "[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]", "[a-zA-Z]+", "[a-zA-Z]+" };
				JTextField[] fields = { textCedula, textRaza, textApodo };

				boolean correcto = Auxiliares.formValido(fields, regex);

				btnIngresar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

				if (correcto) {
					cedula = Integer.parseInt(textCedula.getText().replaceAll("[^0-9]", ""));
					raza = textRaza.getText();
					apodo = textApodo.getText();
					try {
						controller.crtNuevaMascota(apodo, raza, cedula);
						NotificacionExito exito = new NotificacionExito(
								"Mascota insertada correctamente al dueño " + textCedula.getText());
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

	public static JPanel buscarMascota(Controller controller) {

		JPanel nuevo = new JPanel();
		nuevo.setBackground(new Color(60, 179, 113));
		;
		nuevo.setName("Buscar Mascota");

		JTextField textCedula = new JTextField();
		textCedula.setName("cedula");
		textCedula.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		textCedula.setToolTipText("Cedula con formato X.XXX.XXX-X");
		textCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		textCedula.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textCedula.setForeground(Color.LIGHT_GRAY);
		textCedula.setText("...cedula");
		textCedula.setBounds(215, 29, 135, 27);
		textCedula.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		nuevo.add(textCedula);
		textCedula.setColumns(10);

		JLabel lblCedula = new JLabel("Cedula del dueño");
		lblCedula.setLabelFor(textCedula);
		lblCedula.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setBounds(53, 29, 152, 27);
		nuevo.add(lblCedula);

		nuevo.setLayout(null);

		JTextField txtnumInscripcion = new JTextField();
		txtnumInscripcion.setToolTipText("Debe ser un entero positivo");
		txtnumInscripcion.setText("...num Inscripcion");
		txtnumInscripcion.setName("num Inscripcion");
		txtnumInscripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		txtnumInscripcion.setForeground(Color.LIGHT_GRAY);
		txtnumInscripcion.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		txtnumInscripcion.setColumns(10);
		txtnumInscripcion.setBounds(215, 94, 135, 27);
		txtnumInscripcion.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		nuevo.add(txtnumInscripcion);

		JLabel lblNumInsc = new JLabel("Num. Inscripciion");
		lblNumInsc.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumInsc.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblNumInsc.setBounds(53, 94, 152, 27);
		nuevo.add(lblNumInsc);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(53, 146, 297, 54);
		nuevo.add(scrollPane);

		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, new String[] { "Apodo", "Raza" }) {
			Class[] columnTypes = new Class[] { String.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		JTable table_1 = new JTable();
		table_1.setModel(model);
		scrollPane.setViewportView(table_1);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBackground(Color.LIGHT_GRAY);
		btnBuscar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnBuscar.setBounds(150, 219, 115, 27);
		btnBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnBuscar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				btnBuscar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				JTextField[] fields = { textCedula, txtnumInscripcion };
				String[] regex = { "[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]", "[0-9]+" };

				boolean correcto = Auxiliares.formValido(fields, regex);

				if (correcto) {
					int cedula = Integer.parseInt(textCedula.getText().replaceAll("[^0-9]", ""));
					int numInscripcion = Integer.parseInt(txtnumInscripcion.getText());
					VOMascota mascota = null;

					try {
						mascota = controller.ctrObtenerMascota(cedula, numInscripcion);
					} catch (PersistenciaException | SolicitudException e1) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
					} catch (RemoteException e2) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT,
								"Comunicacion interrumpida");
					}

					Auxiliares.vaciarTabla(table_1);
					if (mascota != null) {
						Object[] data = { mascota.getApodo(), mascota.getRaza() };
						model.addRow(data);
					}

				}
			}
		});
		nuevo.add(btnBuscar);

		return nuevo;
	}

	public static JPanel listaMascota(Controller controller) {

		JPanel nuevo = new JPanel();
		nuevo.setName("Listar Mascotas");
		nuevo.setBackground(new Color(60, 179, 113));
		nuevo.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(54, 72, 313, 152);
		nuevo.add(scrollPane);

		JTable table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setFont(new Font("Microsoft Tai Le", Font.PLAIN, 14));
		DefaultTableModel model = new DefaultTableModel(new Object[][] {},
				new String[] { "Apodo", "Raza", "Num Inscripcion" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, true, true };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(91);
		scrollPane.setViewportView(table);

		JLabel lblTitle = new JLabel("Mascotas de :");
		lblTitle.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(54, 11, 313, 25);
		nuevo.add(lblTitle);

		JTextField textCedula = new JTextField();
		textCedula.addFocusListener(Auxiliares.getTextFieldFocusAdapter());
		textCedula.setHorizontalAlignment(SwingConstants.RIGHT);
		textCedula.setName("cedula");
		textCedula.setText("...cedula");
		textCedula.setForeground(Color.LIGHT_GRAY);
		textCedula.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textCedula.setBounds(152, 41, 116, 20);
		nuevo.add(textCedula);
		textCedula.setColumns(10);

		JButton btnCargar = new JButton("Cargar");
		btnCargar.setBackground(Color.LIGHT_GRAY);
		btnCargar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnCargar.setBounds(169, 235, 89, 23);
		nuevo.add(btnCargar);

		btnCargar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnCargar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				btnCargar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				JTextField[] fields = { textCedula };
				String[] regex = { "[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]" };
				boolean correcto = Auxiliares.formValido(fields, regex);

				if (correcto) {
					int cedula = Integer.parseInt(textCedula.getText().replaceAll("[^0-9]", ""));
					List<VOMascotaList> mascotas = new ArrayList<VOMascotaList>();

					try {
						mascotas = controller.crtListarMascotaDueno(cedula);
					} catch (PersistenciaException | SolicitudException e1) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
					} catch (RemoteException e2) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT,
								"Comunicacion interrumpida");
					}

					Auxiliares.vaciarTabla(table);

					for (VOMascotaList mascota : mascotas) {
						Object[] data = { mascota.getApodo(), mascota.getRaza(), mascota.getNumInscripcion() };
						model.addRow(data);
					}

				}
			}
		});

		return nuevo;
	}

	public static JPanel contarMascota(Controller controller) {

		JPanel nuevo = new JPanel();
		nuevo.setName("Contar Mascota");
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

		JLabel lblCedula = new JLabel("Cedula del dueño");
		lblCedula.setLabelFor(textCedula);
		lblCedula.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblCedula.setHorizontalAlignment(SwingConstants.CENTER);
		lblCedula.setBounds(53, 29, 152, 27);
		nuevo.add(lblCedula);
		JTextField textRaza = new JTextField();
		textRaza.setName("raza");

		textRaza.addFocusListener(Auxiliares.getTextFieldFocusAdapter());

		textRaza.setToolTipText("No debe contener caracteres especiales ni numeros");
		textRaza.setText("...raza");
		textRaza.setHorizontalAlignment(SwingConstants.RIGHT);
		textRaza.setForeground(Color.LIGHT_GRAY);
		textRaza.setFont(new Font("Microsoft Tai Le", Font.ITALIC, 14));
		textRaza.setColumns(10);
		textRaza.setBounds(215, 81, 135, 27);
		nuevo.add(textRaza);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setLabelFor(textRaza);
		lblRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblRaza.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblRaza.setBounds(53, 81, 152, 27);
		nuevo.add(lblRaza);

		JButton btnContar = new JButton("Contar");
		btnContar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		btnContar.setBackground(Color.LIGHT_GRAY);
		btnContar.setBounds(166, 134, 89, 23);
		nuevo.add(btnContar);

		JLabel lblResultado = new JLabel("0");
		lblResultado.setFont(new Font("Microsoft Tai Le", Font.BOLD, 40));
		lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
		lblResultado.setBounds(10, 193, 409, 47);
		nuevo.add(lblResultado);

		JLabel lblElDueoPosee = new JLabel("El dueño posee ");
		lblElDueoPosee.setHorizontalAlignment(SwingConstants.CENTER);
		lblElDueoPosee.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblElDueoPosee.setBounds(10, 168, 409, 27);
		nuevo.add(lblElDueoPosee);

		JLabel lblPerrosDeRaza = new JLabel("perros de raza");
		lblPerrosDeRaza.setHorizontalAlignment(SwingConstants.CENTER);
		lblPerrosDeRaza.setFont(new Font("Microsoft Tai Le", Font.BOLD, 14));
		lblPerrosDeRaza.setBounds(10, 237, 409, 27);
		nuevo.add(lblPerrosDeRaza);

		btnContar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				btnContar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}

			@Override
			public void mouseReleased(MouseEvent e) {

				int cedula;
				String raza;
				String[] regex = { "[0-9].[0-9][0-9][0-9].[0-9][0-9][0-9]-[0-9]", "[a-zA-Z]+" };
				JTextField[] fields = { textCedula, textRaza };

				boolean correcto = Auxiliares.formValido(fields, regex);

				btnContar.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));

				if (correcto) {
					cedula = Integer.parseInt(textCedula.getText().replaceAll("[^0-9]", ""));
					raza = textRaza.getText();

					int count = 0;

					try {
						count = controller.ctrlContarMascotas(cedula, raza);
					} catch (PersistenciaException | SolicitudException e1) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_DISPOSE, e1.getMessage());
					} catch (RemoteException e2) {
						ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT,
								"Comunicacion interrumpida");
					}

					lblElDueoPosee.setText("El dueño " + cedula + "posee: ");
					lblPerrosDeRaza.setText("perros de raza " + raza);
					lblResultado.setText(String.valueOf(count));

				}

			}

		});
		return nuevo;
	}

}
