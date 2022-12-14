package grafica.controladores;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import grafica.ventana.DuenosFrames;
import grafica.ventana.MascotasFrames;
import grafica.ventana.TitlePanel;

public class MenuController {

	private JTabbedPane tabs;
	Controller controller;

	public MenuController(JTabbedPane tabbedPane, Controller controller) {
		tabs = tabbedPane;
		this.controller = controller;
	}

	public void insertarDueno(DuenosFrames dueno) {

	}

	public void nuevaMascota() {

		String title = "Nueva Mascota";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();
		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = MascotasFrames.nuevaMascota(controller);
			crearNuevoTab(title, borrar, nuevo);
		}

	}

	public void listadoMascota() {

		String title = "Listado Mascotas";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();
		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = MascotasFrames.listaMascota(controller);
			crearNuevoTab(title, borrar, nuevo);
		}

	}

	public void buscarMascota() {
		String title = "Buscar Mascota";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();

		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = MascotasFrames.buscarMascota(controller);

			crearNuevoTab(title, borrar, nuevo);
		}

	}

	public void contarMascota() {
		String title = "Contar Mascota";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();

		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = MascotasFrames.contarMascota(controller);
			crearNuevoTab(title, borrar, nuevo);

		}

	}

	public boolean cambiarVisibilidadTab() {
		boolean borrar = false;
		if (!tabs.isVisible()) {
			tabs.setVisible(true);
			borrar = true;

		}
		return borrar;
	}

	public void crearNuevoTab(String title, boolean borrar, JPanel nuevo) {
		tabs.addTab(title, nuevo);
		int i = tabs.indexOfTab(title);
		tabs.setTabComponentAt(i, TitlePanel.getCloseTitlePanel(tabs, nuevo, title));
		tabs.setSelectedIndex(i);
		if (borrar) {
			tabs.removeTabAt(0);
		}
	}

	public void nuevoDue??o() {
		String title = "Nuevo Due??o";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();

		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = DuenosFrames.nuevoDueno(controller);
			crearNuevoTab(title, borrar, nuevo);

		}

	}

	public void borrarDue??o() {
		String title = "Borrar Due??o";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();

		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = DuenosFrames.borrarDueno(controller);
			crearNuevoTab(title, borrar, nuevo);

		}

	}

	public void listarDue??o() {
		String title = "Lista Due??os";
		int i = tabs.indexOfTab(title);
		boolean borrar = cambiarVisibilidadTab();

		if (i >= 0) {
			tabs.setSelectedIndex(i);
		} else {
			JPanel nuevo = DuenosFrames.listarDuenos(controller);
			crearNuevoTab(title, borrar, nuevo);

		}

	}
}
