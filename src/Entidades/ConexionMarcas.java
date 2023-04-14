package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionMarcas 
{
	ConexionMySql conexion;
	Connection connection;
	PreparedStatement preStatement;
	
	public ConexionMarcas()
	{
		this.conexion = null;
		this.connection = null;
		this.preStatement = null;
	}
	
	private void conectar()
	{
		this.conexion = new ConexionMySql();
		
			if(this.conexion.conectar())
			{
				this.connection = this.conexion.obtenerConexion();
			}
		
	}
	
	public ListaMarcas obtener_Marcas() throws SQLException
	{
		ListaMarcas retorno = new ListaMarcas();
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Marca INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno.agregar
								 (
										 new Marca
										 (
												resultado.getInt("marca_id"), 
												resultado.getString("marca"),
												new Modelo(resultado.getInt("id_tipo_modelo"), resultado.getString("modelo"))
										 )
								 );
							}
					}
	
					
			}
			catch(Exception e)
			{
				
			}
			finally
			{		
				this.preStatement.close();
				this.connection.close();
				this.conexion.desconectar();
			}
		
		return retorno;

	}
	
	public Marca obtener(int idModelo) throws SQLException
	{
		Marca retorno = null;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Marca INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id WHERE Marca.id_tipo_modelo = ?";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, idModelo);
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno = new Marca
								 (
										resultado.getInt("marca_id"), 
										resultado.getString("marca"),
										new Modelo(resultado.getInt("id_tipo_modelo"), resultado.getString("modelo"))
								 );
								 
							}
					}
	
					
			}
			catch(Exception e)
			{
				
			}
			finally
			{		
				this.preStatement.close();
				this.connection.close();
				this.conexion.desconectar();
			}
		
		return retorno;

	}
	
	
}
