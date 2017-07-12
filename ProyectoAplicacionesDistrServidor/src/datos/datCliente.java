package datos;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

import entidades.entMaeCli;

public class datCliente {
	
	// Singleton
	public static datCliente _Instancia;
	private datCliente() {};
	public static datCliente Instancia() {
		if (_Instancia == null) {
			_Instancia = new datCliente();
		}
		return _Instancia;
	}
	// endSingleton
	
	public entMaeCli verificarAccesoCliente(int nroTarjeta,String clave) throws Exception {
		Connection cn = null;
		entMaeCli cli = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		try {

			cn = Conexion.Instancia().getConnection();
			cstm = cn.prepareCall("{call sp_VerificarAccesoCliente(?,?)}");
			cstm.setInt(1, nroTarjeta);
			cstm.setString(2, clave);
			rs = cstm.executeQuery();
			if (rs.next()) {
				cli = new entMaeCli();
				cli.setId(rs.getInt(1));
				cli.setNombre(rs.getString(2));
				cli.setNumeroTarjeta(rs.getInt(3));
				cli.setClave(rs.getString(4));
				cli.setSaldo(rs.getInt(5));
				cli.setUltMovMonto(rs.getInt(6));
				cli.setFechaUlt(rs.getString(7));
				cli.setHoraUlt(rs.getString(8));
			}
			return cli;
		} catch (Exception e) {
			throw e;
		} finally {
			cn.close();
			cstm.close();
			rs.close();
		}
	}
	
	public boolean nuevoMaeCliente(entMaeCli cli) throws Exception {
		Connection cn = null;
		boolean nuevo = false;
		CallableStatement cstm = null;
		try {
			cn = Conexion.Instancia().getConnection();
			cstm = cn.prepareCall("{call sp_nuevoMaeCliente(?,?,?,?,?,?,?)}");
			cstm.setString(1, cli.getNombre());
			cstm.setInt(2, cli.getNumeroTarjeta());
			cstm.setString(3, cli.getClave());
			cstm.setInt(4, cli.getSaldo());
			cstm.setInt(5, cli.getUltMovMonto());
			cstm.setString(6, cli.getFechaUlt());
			cstm.setString(7, cli.getHoraUlt());
			int x = cstm.executeUpdate();	
			if(x!=0){
				nuevo = true;
			}
			
			return nuevo;
		} catch (Exception e) {
			throw e;
		} finally {
			cn.close();
			cstm.close();
		}
	}
	public entMaeCli DevolverMaeClienteId(int id) throws Exception {
		Connection cn = null;
		entMaeCli cli = null;
		CallableStatement cstm = null;
		ResultSet rs = null;
		try {

			cn = Conexion.Instancia().getConnection();
			cstm = cn.prepareCall("{call sp_DevolverMaeClienteId(?)}");
			cstm.setInt(1, id);
			rs = cstm.executeQuery();
			if (rs.next()) {
				cli = new entMaeCli();
				cli.setId(rs.getInt(1));
				cli.setNombre(rs.getString(2));
				cli.setNumeroTarjeta(rs.getInt(3));
				cli.setClave(rs.getString(4));
				cli.setSaldo(rs.getInt(5));
				cli.setUltMovMonto(rs.getInt(6));
				cli.setFechaUlt(rs.getString(7));
				cli.setHoraUlt(rs.getString(8));
			}
			return cli;
		} catch (Exception e) {
			throw e;
		} finally {
			cn.close();
			cstm.close();
			rs.close();
		}
	}
	
	public boolean EditarMaeCliente(entMaeCli cli) throws Exception {
		Connection cn = null;
		boolean editar = false;
		CallableStatement cstm = null;
		try {
			cn = Conexion.Instancia().getConnection();
			cstm = cn.prepareCall("{call sp_EditarMaeCliente(?,?,?,?,?)}");
			cstm.setInt(1, cli.getId());
			//cstm.setString(2, cli.getNombre());
			//cstm.setInt(3, cli.getNumeroTarjeta());
			//cstm.setString(4, cli.getClave());
			cstm.setInt(2, cli.getSaldo());
			cstm.setInt(3, cli.getUltMovMonto());
			cstm.setString(4, cli.getFechaUlt());
			cstm.setString(5, cli.getHoraUlt());
			int x = cstm.executeUpdate();
			
			if(x!=0){
				editar = true;
			}
			
			return editar;
		} catch (Exception e) {
			throw e;
		} finally {
			cn.close();
			cstm.close();
		}
	}
}
