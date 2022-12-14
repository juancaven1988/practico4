package grafica.ventana;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import grafica.controladores.Controller;
import grafica.controladores.MenuController;

public class MenuBar extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = 1L;
	MenuController controller;

	public MenuBar(JTabbedPane tabbedPane, Controller mainController) {

		this.setBorderPainted(false);
		this.setBorder(null);
		this.setBackground(new Color(255, 255, 255));
		controller = new MenuController(tabbedPane, mainController);

		JMenu mnMenu = new JMenu("Inicio");
		mnMenu.setMnemonic(KeyEvent.VK_I);
		mnMenu.setBorder(null);
		this.add(mnMenu);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(this);
		mntmSalir.setActionCommand("Salir");
		mntmSalir.setBackground(Color.WHITE);
		mnMenu.add(mntmSalir);

		JMenu mndueo = new JMenu("Dueños");
		mndueo.setMnemonic(KeyEvent.VK_D);
		mndueo.setBorder(null);
		this.add(mndueo);

		JMenuItem mntmInsertar = new JMenuItem("Insertar");
		mntmInsertar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.ALT_DOWN_MASK));
		mntmInsertar.addActionListener(this);
		mntmInsertar.setActionCommand("Insertar");

		mntmInsertar.setBackground(Color.WHITE);
		mndueo.add(mntmInsertar);

		JMenuItem mntmBorrar = new JMenuItem("Borrar");
		mntmBorrar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.ALT_DOWN_MASK));
		mntmBorrar.setBackground(Color.WHITE);
		mntmBorrar.addActionListener(this);
		mntmBorrar.setActionCommand("Borrar");
		mndueo.add(mntmBorrar);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.ALT_DOWN_MASK));
		mntmListar.setBackground(Color.WHITE);
		mntmListar.addActionListener(this);
		mntmListar.setActionCommand("Listar");
		mndueo.add(mntmListar);

		JMenu mnmascotas = new JMenu("Mascotas");
		mnmascotas.setMnemonic(KeyEvent.VK_M);
		mnmascotas.setBorder(null);
		this.add(mnmascotas);

		JMenuItem mntmNueva = new JMenuItem("Nueva");
		mntmNueva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		mntmNueva.addActionListener(this);
		mntmNueva.setActionCommand("Nueva");
		mntmNueva.setBackground(Color.WHITE);
		mnmascotas.add(mntmNueva);

		JMenuItem mntmListado = new JMenuItem("Listado");
		mntmListado.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
		mntmListado.addActionListener(this);
		mntmListado.setActionCommand("Listado");
		mntmListado.setBackground(Color.WHITE);
		mnmascotas.add(mntmListado);

		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
		mntmBuscar.addActionListener(this);
		mntmBuscar.setActionCommand("Buscar");
		mntmBuscar.setBackground(Color.WHITE);
		mnmascotas.add(mntmBuscar);

		JMenuItem mntmContar = new JMenuItem("Contar");
		mntmContar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
		mntmContar.addActionListener(this);
		mntmContar.setActionCommand("Contar");
		mntmContar.setBackground(Color.WHITE);
		mnmascotas.add(mntmContar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String comand = e.getActionCommand();

		switch (comand) {
		case "Insertar":
			controller.nuevoDueño();
			break;
		case "Borrar":
			controller.borrarDueño();
			break;
		case "Listar":
			controller.listarDueño();
			break;
		case "Nueva":
			controller.nuevaMascota();
			break;
		case "Listado":
			controller.listadoMascota();
			break;
		case "Buscar":
			controller.buscarMascota();
			break;
		case "Contar":
			controller.contarMascota();
			break;
		case "Salir":
			System.exit(0);
			break;
		}

	}

}
