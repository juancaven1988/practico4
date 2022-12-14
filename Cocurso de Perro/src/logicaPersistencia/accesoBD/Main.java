package logicaPersistencia.accesoBD;

import java.io.FileInputStream;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Main {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		try {

			Properties p = new Properties();
			String propiedades = "config/data.properties";
			p.load(new FileInputStream(propiedades));
			String ip = p.getProperty("ipServidor");
			String puerto = p.getProperty("puertoServidor");

			System.setProperty("java.rmi.server.hostname", ip);
			LocateRegistry.createRegistry(Integer.parseInt(puerto));
			Fachada fachada = Fachada.getInstancia();

			Naming.rebind("//" + ip + ":" + puerto + "/fachada", fachada);

			JOptionPane.showMessageDialog(null, "Servidor Iniciado", "Server", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, e.getMessage(), "Server", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);

		}
	}

}
