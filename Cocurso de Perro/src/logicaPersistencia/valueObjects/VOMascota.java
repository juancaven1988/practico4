package logicaPersistencia.valueObjects;

import java.io.Serializable;

public class VOMascota  implements Serializable  {


	private static final long serialVersionUID = 1L; 

	private String apodo;
	private String raza;
	
	public VOMascota(String apodo, String raza) {
		
		this.apodo = apodo;
		this.raza = raza;
	}

	public String getApodo() {
		return apodo;
	}

	public String getRaza() {
		return raza;
	}
	
	
}
