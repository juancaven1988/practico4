package logicaPersistencia.accesoBD;

public class Consultas {

	public static String buscarDueno() {

		return "Select * from duenos where cedula = (?)";
	}

	public static String insertarDueno() {

		return "Insert into Duenos (cedula, nombre, apellido) values ((?),(?),(?))";
	}

	public static String buscarUltimoNumInscripcion() {

		return "Select numInscripcion From mascotas where cedDueno = (?) order by numInscripcion Desc Limit 1";
	}

	public static String InsertarMascota() {

		return "Insert into mascotas(numInscripcion, apodo, raza, cedDueno) values ((?),(?),(?),(?))";
	}

	public static String borrarDueno() {

		return "Delete from duenos where cedula = (?)";
	}

	public static String buscarMascotas() {

		return "Select * from mascotas where cedDueno = (?)";
	}

	public static String borrarMascota() {

		return "Delete from mascotas where numInscripcion = (?) and cedDueno = (?)";
	}

	public static String listarDuenos() {

		return "Select * from duenos order by cedula asc";
	}

	public static String buscarMascota() {

		return "Select * from mascotas where cedDueno = (?) and numInscripcion = (?)";
	}

	public static String buscarMascotasRaza() {

		return "Select * from mascotas where cedDueno = (?) and raza = (?)";
	}

}
