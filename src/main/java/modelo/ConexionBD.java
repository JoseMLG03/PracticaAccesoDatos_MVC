package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexionBD {
	
	private Connection con;
	
	public ConexionBD( String host, String port, String user, String pswd) {
		super();
		this.con = conectar(host,port,user,pswd);
	}
	private Connection conectar(String host,String port,String user,String pswd) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String URL="jdbc:mysql://"+host+": "+port;
			Connection con1 = DriverManager.getConnection(URL,user,pswd);
			//JOptionPane.showMessageDialog(null, "Conexion Establecida");
			return con1;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Conexion Fallida");
			e.printStackTrace();
			return null;
		}
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	
}
