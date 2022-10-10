package logicaPersistencia.valueObjects;

public class VOMascotaList extends VOMascota {

	private static final long serialVersionUID = 1L;
	private int numInscripcion;

	public VOMascotaList(int numInscripcion, String apodo, String raza) {
		super(apodo, raza);
		this.numInscripcion = numInscripcion;
	}

	public int getNumInscripcion() {
		return numInscripcion;
	}
	
	
}
