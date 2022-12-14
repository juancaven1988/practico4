package logicaPersistencia.accesoBD;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.List;

import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.SolicitudException;
import logicaPersistencia.valueObjects.VODueno;
import logicaPersistencia.valueObjects.VOMascota;
import logicaPersistencia.valueObjects.VOMascotaList;

public class Fachada extends UnicastRemoteObject implements iFachada, Serializable {

	private static final long serialVersionUID = 1L;
	private static Fachada instancia;
	private IPoolConexiones pool;

	private Fachada() throws PersistenciaException, RemoteException {

		try {

			pool = new PoolConexiones();

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

		IConexion icon = null;
		try {

			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			boolean existe = AccesoBD.BuscarDueno(con, voD.getCedula());

			if (!existe) {

				AccesoBD.insertarDueno(con, voD);
			} else {
				pool.liberarConexion(icon, false);
				throw new SolicitudException("Dueno ya existe");
			}

			pool.liberarConexion(icon, true);

		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		} catch (PersistenciaException e) {
			pool.liberarConexion(icon, false);
			throw e;
		}

	}

	public void nuevaMascota(VOMascota voM, int ced) throws PersistenciaException, SolicitudException, RemoteException {

		IConexion icon = null;

		try {

			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {
				AccesoBD.insertarMascota(ced, voM, con);

			} else {
				pool.liberarConexion(icon, false);
				throw new SolicitudException("Dueno no existente");
			}

			pool.liberarConexion(icon, true);

		} catch (PersistenciaException e1) {

			pool.liberarConexion(icon, false);
			throw e1;
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		}

	}

	public void borrarDuenoMascota(int ced) throws PersistenciaException, SolicitudException, RemoteException {
		IConexion icon = null;
		try {

			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				AccesoBD.borrarDueno(con, ced);
			} else {
				pool.liberarConexion(icon, false);
				throw new SolicitudException("Dueno no existe");
			}

			pool.liberarConexion(icon, true);

		} catch (PersistenciaException e1) {

			pool.liberarConexion(icon, false);
			throw e1;
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		}

	}

	public List<VODueno> listarDuenos() throws PersistenciaException, RemoteException {

		List<VODueno> duenos = null;
		IConexion icon = null;

		try {

			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			duenos = AccesoBD.listarDuenos(con);

			pool.liberarConexion(icon, true);

		} catch (PersistenciaException e1) {

			pool.liberarConexion(icon, false);
			throw e1;
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		}

		return duenos;
	}

	public List<VOMascotaList> listarMascotaDueno(int ced)
			throws PersistenciaException, SolicitudException, RemoteException {

		List<VOMascotaList> mascotas = null;

		IConexion icon = null;
		try {
			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				mascotas = AccesoBD.listaMascotas(con, ced);

			} else {
				pool.liberarConexion(icon, false);
				throw new SolicitudException("Dueno no existe");
			}

			pool.liberarConexion(icon, true);
		} catch (PersistenciaException e1) {

			pool.liberarConexion(icon, false);
			throw e1;
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		}

		return mascotas;
	}

	public VOMascota obtenerMascota(int ced, int num)
			throws PersistenciaException, SolicitudException, RemoteException {

		VOMascota mascota = null;

		IConexion icon = null;
		try {

			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				boolean existeMascota = AccesoBD.buscarMascota(con, ced, num);

				if (existeMascota) {
					mascota = AccesoBD.obtenerMascota(con, ced, num);
				} else {
					pool.liberarConexion(icon, false);
					throw new SolicitudException("Mascota no existe");
				}

			} else {
				pool.liberarConexion(icon, false);
				throw new SolicitudException("Dueno no existe");
			}

			pool.liberarConexion(icon, true);
			;

		} catch (PersistenciaException e1) {

			pool.liberarConexion(icon, false);
			throw e1;
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		}

		return mascota;
	}

	public int contarMascotas(int ced, String raza) throws PersistenciaException, SolicitudException, RemoteException {

		int count = 0;
		IConexion icon = null;

		try {

			icon = pool.obtenerConexion(true);
			Connection con = ((Conexion) icon).getConexion();

			boolean existe = AccesoBD.BuscarDueno(con, ced);

			if (existe) {

				count = AccesoBD.contarMascotas(con, ced, raza);

			} else {
				pool.liberarConexion(icon, false);
				throw new SolicitudException("Dueno no existe");
			}

			pool.liberarConexion(icon, true);

		} catch (PersistenciaException e1) {

			pool.liberarConexion(icon, false);
			throw e1;
		} catch (InterruptedException e) {
			throw new PersistenciaException("Error de persistencia");
		}

		return count;
	}

}
