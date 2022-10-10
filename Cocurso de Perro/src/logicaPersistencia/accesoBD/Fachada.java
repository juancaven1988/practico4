package logicaPersistencia.accesoBD;

import java.io.FileInputStream;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.SolicitudException;
import logicaPersistencia.valueObjects.VODueno;
import logicaPersistencia.valueObjects.VOMascota;
import logicaPersistencia.valueObjects.VOMascotaList;

public class Fachada extends UnicastRemoteObject implements iFachada, Serializable {

	private static final long serialVersionUID = 1L;
	private static Fachada instancia;
	private String url;
	private String user;
	private String pass;

	private Fachada() throws PersistenciaException, RemoteException {

		Properties p = new Properties();
		String properties = "config/data.properties";

		try {
			p.load(new FileInputStream(properties));
			url = p.getProperty("url");
			user = p.getProperty("user");
			pass = p.getProperty("sena");

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");

		}

	}

	public static Fachada getInstancia() throws PersistenciaException, RemoteException {

		if (instancia == null) {

			instancia = new Fachada();

		}

		return instancia;

	}

	public void nuevoDueno(VODueno voD) throws PersistenciaException, SolicitudException, RemoteException {

		try {

			Connection con = DriverManager.getConnection(url, user, pass);

			boolean existe = AccesoBD.BuscarDueno(con, voD.getCedula());

			if (!existe) {

				AccesoBD.insertarDueno(con, voD);
			} else {
				con.close();
				throw new SolicitudException("Dueno ya existe");
			}

			con.close();

		} catch (SQLException e) {

			throw new PersistenciaException("Error de persistencia");
		}

	}

	public void nuevaMascota(VOMascota voM, int ced) throws PersistenciaException, SolicitudException, RemoteException {

		try {

			Connection con = DriverManager.getConnection(url, user, pass);

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {
				AccesoBD.insertarMascota(ced, voM, con);

			} else {
				con.close();
				throw new SolicitudException("Dueno no existente");
			}

			con.close();

		} catch (SQLException e) {

			throw new PersistenciaException("Error de persistencia");
		}

	}

	public void borrarDuenoMascota(int ced) throws PersistenciaException, SolicitudException, RemoteException {

		try {

			Connection con = DriverManager.getConnection(url, user, pass);

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				AccesoBD.borrarDueno(con, ced);
			} else {
				con.close();
				throw new SolicitudException("Dueno no existe");
			}

			con.close();

		} catch (SQLException e) {

			throw new PersistenciaException("Error de persistencia");
		}

	}

	public List<VODueno> listarDuenos() throws PersistenciaException, RemoteException {

		List<VODueno> duenos = null;

		try {

			Connection con = DriverManager.getConnection(url, user, pass);

			duenos = AccesoBD.listarDuenos(con);

			con.close();

		} catch (SQLException e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return duenos;
	}

	public List<VOMascotaList> listarMascotaDueno(int ced)
			throws PersistenciaException, SolicitudException, RemoteException {

		List<VOMascotaList> mascotas = null;

		Connection con;
		try {
			con = DriverManager.getConnection(url, user, pass);

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				mascotas = AccesoBD.listaMascotas(con, ced);

			} else {
				con.close();
				throw new SolicitudException("Dueno no existe");
			}

			con.close();
		} catch (SQLException e) {
			throw new PersistenciaException("Error de Persistencia");
		}

		return mascotas;
	}

	public VOMascota obtenerMascota(int ced, int num)
			throws PersistenciaException, SolicitudException, RemoteException {

		VOMascota mascota = null;

		try {

			Connection con = DriverManager.getConnection(url, user, pass);

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				boolean existeMascota = AccesoBD.buscarMascota(con, ced, num);

				if (existeMascota) {
					mascota = AccesoBD.obtenerMascota(con, ced, num);
				} else {
					con.close();
					throw new SolicitudException("Mascota no existe");
				}

			} else {
				con.close();
				throw new SolicitudException("Dueno no existe");
			}

			con.close();

		} catch (SQLException e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return mascota;
	}

	public int contarMascotas(int ced, String raza) throws PersistenciaException, SolicitudException, RemoteException {

		int count = 0;

		try {

			Connection con = DriverManager.getConnection(url, user, pass);

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				count = AccesoBD.contarMascotas(con, ced, raza);

			} else {
				con.close();
				throw new SolicitudException("Dueno no existe");
			}

			con.close();

		} catch (SQLException e) {

			throw new PersistenciaException("Error de persistencia");
		}
		// TODO Auto-generated method stub
		return count;
	}

}
