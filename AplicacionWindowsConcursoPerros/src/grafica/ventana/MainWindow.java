package grafica.ventana;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.rmi.NotBoundException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import grafica.controladores.Controller;

public class MainWindow {

	private JFrame mainFrame;
	private Controller controller;
	MenuBar menuBar;

	public MainWindow() {

		try {
			controller = new Controller();
			initialize();
			mainFrame.setVisible(true);
		} catch (IOException | NotBoundException e) {

			ExceptionWindow error = new ExceptionWindow(ExceptionWindow.ACCION_EXIT, "No se encuentra el servidor");
		}

	}

	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setTitle("Concurso de Mascotas");
		mainFrame.setIconImage(
				Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/grafica/imagenes/dogIcon.png")));
		mainFrame.getContentPane().setBackground(new Color(30, 144, 255));
		mainFrame.setBounds(100, 100, 450, 359);
		mainFrame.getContentPane().setLayout(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocationRelativeTo(null);

		UIManager.put("TabbedPane.selected", Color.WHITE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setName("tabbedPane");
		tabbedPane.setRequestFocusEnabled(false);
		tabbedPane.setFocusable(false);
		tabbedPane.setMinimumSize(new Dimension(10, 10));
		tabbedPane.setPreferredSize(new Dimension(10, 10));
		tabbedPane.setBackground(Color.LIGHT_GRAY);
		tabbedPane.setBounds(0, 0, 434, 320);
		mainFrame.getContentPane().add(tabbedPane);

		menuBar = new MenuBar(tabbedPane, controller);
		mainFrame.setJMenuBar(menuBar);

		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {

				JPanel selected = (JPanel) tabbedPane.getComponentAt(tabbedPane.getSelectedIndex());
				String title = selected.getName();
				tabbedPane.setTabComponentAt(tabbedPane.getSelectedIndex(),
						TitlePanel.getCloseTitlePanel(tabbedPane, selected, title));

				for (int i = 0; i < tabbedPane.getTabCount(); i++) {
					if (i != tabbedPane.getSelectedIndex()) {

						String name = tabbedPane.getComponentAt(i).getName();
						tabbedPane.setTabComponentAt(i, TitlePanel.getNormalTitlePanel(tabbedPane, name));
						tabbedPane.setBackgroundAt(i, Color.LIGHT_GRAY);
					}
				}
			}
		});
	}

}
