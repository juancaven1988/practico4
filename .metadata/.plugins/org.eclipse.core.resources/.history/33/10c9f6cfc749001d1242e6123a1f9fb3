package logicaPersistencia.accesoBD;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import logicaPersistencia.excepciones.PersistenciaException;

public class PoolConexiones implements IPoolConexiones {

	private String url;
	private String user;
	private String pass;
	private int nivelTransacionalidad;
	private Conexion[] conexiones;
	private int tamano;
	private int creadas;
	private int tope;

	public PoolConexiones() throws PersistenciaException {
		Properties p = new Properties();
		String properties = "config/data.properties";

		try {
			p.load(new FileInputStream(properties));
			url = p.getProperty("url");
			user = p.getProperty("user");
			pass = p.getProperty("sena");
			tamano = Integer.parseInt(p.getProperty("tamano"));
			conexiones = new Conexion[tamano];
			creadas = 0;
			tope = 0;
			nivelTransacionalidad = Integer.parseInt(p.getProperty("nivel_transaccion"));

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");

		}

	}

	public synchronized IConexion obtenerConexion(boolean modifica) throws PersistenciaException, InterruptedException {

		Connection con = null;
		IConexion retorno = null;
		while (retorno == null) {
			if (tope > 0) {
				retorno = conexiones[tope - 1];
				tope--;
			} else if (creadas < tamano) {
				try {
					con = DriverManager.getConnection(url, user, pass);
					con.setAutoCommit(false);
					con.setTransactionIsolation(nivelTransacionalidad);
					creadas++;
					retorno = new Conexion(con);
				} catch (SQLException e) {
					throw new PersistenciaException("Error de persistencia");
				}
			} else {
				wait();
			}
		}

		return retorno;
	}

	public synchronized void liberarConexion(IConexion con, boolean exito) throws PersistenciaException {

		tope++;
		conexiones[tope - 1] = (Conexion) con;
		notifyAll();

		try {
			if (exito) {
				((Conexion) con).getConexion().commit();
			} else {
				((Conexion) con).getConexion().rollback();
			}
		} catch (Exception e) {
			new PersistenciaException("Error de persistencia");
		}

	}

}
