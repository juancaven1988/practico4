package logicaPersistencia.accesoBD;

import java.sql.Connection;

public class Conexion implements IConexion {

	private Connection connection;

	public Conexion(Connection connection) {
		this.connection = connection;
	}

	public Connection getConexion() {
		return connection;
	}

}
