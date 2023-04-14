package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class ConexionObservaciones 
{
	ConexionMySql conexion;
	Connection connection;
	PreparedStatement preStatement;
	
	public ConexionObservaciones()
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
	
	public void insertar(Observacion observacion) throws SQLException
	{
		try
		{
			this.conectar();		
			
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO Observacion (detalle, aprobacion, id_inspector) VALUES (?, ?, ?)";

					
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setString(1, observacion.detalle());
						preStatement.setString(2, observacion.aprobacion());
						preStatement.setInt(3, observacion.id_inspector());
						preStatement.execute();
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

	}
	
	public void modificar(Observacion observacion) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						String consulta = "UPDATE Observacion SET detalle = ?, aprobacion = ?, id_inspector = ? WHERE Observacion.observacion_id = ?;";
						
						
						this.preStatement = this.connection.prepareStatement(consulta);
						preStatement.setString(1, observacion.detalle());
						preStatement.setString(2, observacion.aprobacion());
						preStatement.setInt(3, observacion.id_inspector());
						preStatement.setInt(4, observacion.id());
						
						preStatement.executeUpdate();
							

					}
	
					
			}
			catch(Exception e)
			{
				System.out.print(e.toString());
			}
			finally
			{		
				this.preStatement.close();
				this.connection.close();
				this.conexion.desconectar();
			}
		

	}
	
	public void eliminarObservacion(int id) throws SQLException
	{
			try
			{
			
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						String consulta = "DELETE FROM Observacion WHERE Observacion.observacion_id = ?;";
				
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, id);
							preStatement.executeUpdate();
							
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
		

	}
	
	
	public Observacion obtener(int id) throws SQLException
	{
		Observacion retorno = null;
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Observacion WHERE Observacion.observacion_id = ?;";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, id);
							
	
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								//System.out.print("ID Persona: " + resultado.getInt("vehiculo_id_persona"));
								retorno = new Observacion
									(
										resultado.getInt("observacion_id"),
										resultado.getString("detalle"),
										resultado.getString("aprobacion"),
										resultado.getInt("id_inspector")
							
									);
								

										
							}
					}
					//System.out.print("SIZ: " + retorno.size());
					
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
	
	public int obtener_idMaximo() throws SQLException
	{
		int retorno = -1;
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT MAX(observacion_id) FROM Observacion;";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
	
							resultado = preStatement.executeQuery();				
							
							if(resultado.next())
							{
								//System.out.print("");
								retorno = resultado.getInt("MAX(observacion_id)");					
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
