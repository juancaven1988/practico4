package grafica.controladores;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Properties;

import logicaPersistencia.accesoBD.iFachada;
import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.SolicitudException;
import logicaPersistencia.valueObjects.VODueno;
import logicaPersistencia.valueObjects.VOMascota;
import logicaPersistencia.valueObjects.VOMascotaList;

public class Controller extends UnicastRemoteObject {

	private static final long serialVersionUID = 1L;
	iFachada fachada;

	public Controller() throws FileNotFoundException, IOException, NotBoundException {

		Properties propiedades = new Properties();
		String prop = "config/data.properties";
		propiedades.load(new FileInputStream(prop));
		String ip = propiedades.getProperty("ipServidor");
		String puerto = propiedades.getProperty("puertoServidor");

		fachada = (iFachada) Naming.lookup("//" + ip + ":" + puerto + "/fachada");

	}

	public void crtNuevoDueno(int cedula, String nombre, String apellido)
			throws RemoteException, PersistenciaException, SolicitudException {
		VODueno dueno = new VODueno(cedula, nombre, apellido);
		fachada.nuevoDueno(dueno);

	}

	public void crtNuevaMascota(String apodo, String raza, int ced)
			throws RemoteException, PersistenciaException, SolicitudException {

		VOMascota mascota = new VOMascota(apodo, raza);
		fachada.nuevaMascota(mascota, ced);
	}

	public void crtBorrarDuenoMascota(int ced) throws RemoteException, PersistenciaException, SolicitudException {
		fachada.borrarDuenoMascota(ced);
	}

	public List<VODueno> crtListarDuenos() throws RemoteException, PersistenciaException {

		return fachada.listarDuenos();
	}

	public List<VOMascotaList> crtListarMascotaDueno(int ced)
			throws RemoteException, PersistenciaException, SolicitudException {

		return fachada.listarMascotaDueno(ced);
	}

	public VOMascota ctrObtenerMascota(int ced, int num)
			throws RemoteException, PersistenciaException, SolicitudException {

		return fachada.obtenerMascota(ced, num);
	}

	public int ctrlContarMascotas(int ced, String raza)
			throws RemoteException, PersistenciaException, SolicitudException {

		return fachada.contarMascotas(ced, raza);
	}

}
