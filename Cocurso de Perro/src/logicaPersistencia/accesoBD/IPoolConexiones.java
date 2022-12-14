package logicaPersistencia.accesoBD;

import logicaPersistencia.excepciones.PersistenciaException;

public interface IPoolConexiones {

	public IConexion obtenerConexion(boolean modifica) throws PersistenciaException, InterruptedException;

	public void liberarConexion(IConexion con, boolean exito) throws PersistenciaException;

}
