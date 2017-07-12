package negocio;

import datos.datCliente;
import entidades.entMaeCli;

public class negCliente {
	// Singleton
	public static negCliente _Instancia;
	private negCliente() {};
	public static negCliente Instancia() {
		if (_Instancia == null) {
			_Instancia = new negCliente();
		}
		return _Instancia;
	}
	// endSingleton
	
	public entMaeCli verificarAccesoCliente(int nroTarjeta,String clave)throws Exception{
		try {
			entMaeCli mc = datCliente.Instancia().verificarAccesoCliente(nroTarjeta, clave);
			return mc;
		} catch (Exception e) {
			throw e;
		}
	}
	
	public boolean nuevoCliente(entMaeCli cliente)throws Exception{
		try {
			boolean x = datCliente.Instancia().nuevoMaeCliente(cliente);
			return x;		
		} catch (Exception e) {
			throw e;
			
		}
	}
	public entMaeCli DevolverMaeClienteId(int id)throws Exception{
		try {
			entMaeCli mc = datCliente.Instancia().DevolverMaeClienteId(id);
			return mc;
		} catch (Exception e) {
			throw e;
		}
	}
	public boolean editarMaeCliente(entMaeCli cliente)throws Exception{
		try {
			 boolean x = datCliente.Instancia().EditarMaeCliente(cliente);
			 return x;
		} catch (Exception e) {
			throw e;
		}
	}
}
