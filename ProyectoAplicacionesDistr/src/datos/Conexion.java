package datos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
	//Singleton
	public static Conexion _Instancia;
	private Conexion(){};
	public static Conexion Instancia(){
		if(_Instancia==null){
			_Instancia = new Conexion();
		}
		return _Instancia;
	}
	//endSingleton

	public Connection getConnection() throws Exception {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cajero","root","MysqlAdmin");
			System.out.println("Exito Conexion a la BD");
		} catch (Exception e) {
			System.out.println("Error Conexion a la BD " + e.getMessage());
			e.printStackTrace();
			System.out.println(e);
		}
		return con;
	}
}
