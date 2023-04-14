package Entidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySql 
{
	Connection conexion;
	String url;
	
	public ConexionMySql()
	{
		this.conexion = null;
		this.url = "jdbc:mysql://localhost:3306/aplicacion_prueba_tecnica_vtv?useUnicode=true&use"+
				"JDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&"
				+ "serverTimezone=UTC";
	}
	
	
	public Connection obtenerConexion()
	{
		return this.conexion;
	}
	
	public boolean conectar()
	{
		boolean retorno = false;
			try
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				
				this.conexion = DriverManager.getConnection(this.url, "root", "sql123");
					
					if(this.conexion != null)
					{
						retorno = true;
						//System.out.print("BIEEEEEEN!");
					}				
				
			}
			catch(ClassNotFoundException e)
			{
				System.out.print("Error en NOT FOUND");
				e.printStackTrace();
			}
			catch(SQLException e)
			{
				System.out.print("Error en SQLEXEP");
			}
			
		return retorno;
	}	
	
	public void desconectar()
	{
		this.conexion = null;
	}
}
