package logicaPersistencia.accesoBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logicaPersistencia.excepciones.PersistenciaException;
import logicaPersistencia.valueObjects.VODueno;
import logicaPersistencia.valueObjects.VOMascota;
import logicaPersistencia.valueObjects.VOMascotaList;

public class AccesoBD {

	public static boolean BuscarDueno(Connection con, int cedula) throws PersistenciaException {

		boolean resultado = false;
		try {

			String query = Consultas.buscarDueno();
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, cedula);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				resultado = true;
			}

			rs.close();
			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return resultado;
	}

	public static void insertarDueno(Connection con, VODueno voD) throws PersistenciaException {

		String query = Consultas.insertarDueno();

		try {

			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, voD.getCedula());
			pstm.setString(2, voD.getNombre());
			pstm.setString(3, voD.getApellido());

			pstm.executeUpdate();

			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

	}

	public static void insertarMascota(int ced, VOMascota voM, Connection con) throws PersistenciaException {

		String query = Consultas.buscarUltimoNumInscripcion();

		try {
			int numInsc = 1;
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, ced);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				numInsc = rs.getInt("numInscripcion") + 1;
			}

			rs.close();
			pstm.close();

			query = Consultas.InsertarMascota();
			pstm = con.prepareStatement(query);
			pstm.setInt(1, numInsc);
			pstm.setString(2, voM.getApodo());
			pstm.setString(3, voM.getRaza());
			pstm.setInt(4, ced);

			pstm.executeUpdate();

			pstm.close();

		} catch (Exception e) {
			throw new PersistenciaException("Error de persistencia");
		}

	}

	public static void borrarDueno(Connection con, int ced) throws PersistenciaException {

		try {

			List<VOMascotaList> mascotas = listaMascotas(con, ced);

			for (VOMascotaList mascota : mascotas) {
				borrarMascota(con, mascota.getNumInscripcion(), ced);
			}

			String query = Consultas.borrarDueno();
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, ced);

			pstm.executeUpdate();

			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

	}

	public static List<VOMascotaList> listaMascotas(Connection con, int ced) throws PersistenciaException {

		ArrayList<VOMascotaList> mascotas = new ArrayList<VOMascotaList>();

		try {
			String query = Consultas.buscarMascotas();

			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, ced);
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				VOMascotaList mascota = new VOMascotaList(rs.getInt("numInscripcion"), rs.getString("apodo"),
						rs.getString("raza"));
				mascotas.add(mascota);
			}

			rs.close();
			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return mascotas;
	}

	private static void borrarMascota(Connection con, int numInsc, int ced) throws PersistenciaException {

		String query = Consultas.borrarMascota();

		try {

			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, numInsc);
			pstm.setInt(2, ced);

			pstm.executeUpdate();
			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

	}

	public static List<VODueno> listarDuenos(Connection con) throws PersistenciaException {

		ArrayList<VODueno> duenos = new ArrayList<VODueno>();
		try {
			String query = Consultas.listarDuenos();

			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				VODueno dueno = new VODueno(rs.getInt("cedula"), rs.getString("nombre"), rs.getString("apellido"));
				duenos.add(dueno);
			}

			rs.close();
			stm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return duenos;
	}

	public static boolean buscarMascota(Connection con, int ced, int num) throws PersistenciaException {

		boolean resultado = false;
		try {

			String query = Consultas.buscarMascota();
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, ced);
			pstm.setInt(2, num);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				resultado = true;
			}

			rs.close();
			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return resultado;
	}

	public static VOMascota obtenerMascota(Connection con, int ced, int num) throws PersistenciaException {

		VOMascota mascota = null;

		try {

			String query = Consultas.buscarMascota();
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, ced);
			pstm.setInt(2, num);

			ResultSet rs = pstm.executeQuery();

			rs.next();

			mascota = new VOMascota(rs.getString("apodo"), rs.getString("raza"));

			rs.close();
			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return mascota;
	}

	public static int contarMascotas(Connection con, int ced, String raza) throws PersistenciaException {

		int count = 0;

		try {

			String query = Consultas.buscarMascotasRaza();
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, ced);
			pstm.setString(2, raza);

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				count++;
			}

			rs.close();
			pstm.close();

		} catch (Exception e) {

			throw new PersistenciaException("Error de persistencia");
		}

		return count;
	}

}
