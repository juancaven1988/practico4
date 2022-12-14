package logicaPersistencia.accesoBD;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.excepciones.SolicitudException;
import logicaPersistencia.valueObjects.VODueno;
import logicaPersistencia.valueObjects.VOMascota;
import logicaPersistencia.valueObjects.VOMascotaList;

public interface iFachada extends Remote {

	public void nuevoDueno(VODueno voD) throws PersistenciaException, SolicitudException, RemoteException;
	
	public void nuevaMascota(VOMascota voM, int ced) throws PersistenciaException, SolicitudException, RemoteException;

	public void borrarDuenoMascota(int ced) throws PersistenciaException, SolicitudException, RemoteException;
	
	public List<VODueno> listarDuenos() throws PersistenciaException, RemoteException;
	
	public List<VOMascotaList> listarMascotaDueno(int ced) throws PersistenciaException, SolicitudException, RemoteException;
	
	public VOMascota obtenerMascota(int ced, int num) throws PersistenciaException, SolicitudException, RemoteException;
	
	public int contarMascotas(int ced, String raza) throws PersistenciaException, SolicitudException, RemoteException;
	
}
