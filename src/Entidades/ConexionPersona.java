package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConexionPersona   
{

	ConexionMySql conexion;
	Connection connection;
	PreparedStatement preStatement;
	
	public ConexionPersona()
	{
		this.conexion = null;
		this.connection = null;
		this.preStatement = null;
	}
	
	protected void conectar()
	{
		this.conexion = new ConexionMySql();
		
			if(this.conexion.conectar())
			{
				this.connection = this.conexion.obtenerConexion();
			}
		
	}
	
	public void insertarPersona(Persona persona) throws SQLException
	{
		try
		{
			this.conectar();		
			
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO Persona (nombre, apellido, edad, dni) VALUES (?,?,?,?)";

					
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setString(1, persona.nombre());
						preStatement.setString(2, persona.apellido());
						preStatement.setInt(3, persona.edad());
						preStatement.setInt(4, persona.dni());
						preStatement.execute();
						preStatement.close();
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
	
	//MODIFICAR
	public void modificar(Persona persona) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						//ResultSet resultado = null;
						String consulta = "UPDATE Persona SET nombre = ?, apellido = ?, edad = ?, dni = ? WHERE Persona.persona_id = ?;";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setString(1, persona.nombre());
							preStatement.setString(2, persona.apellido());
							preStatement.setInt(3, persona.edad());
							preStatement.setInt(4, persona.dni());
							preStatement.setInt(5, persona.id());
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
	
	//ELIMINAR
	public void eliminar(int id) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						ConexionInspector conexionInspector = new ConexionInspector();
						
							if(conexionInspector.existe(id))
							{
								conexionInspector.eliminar(id);
							}
						
						String consulta = "DELETE FROM Persona WHERE Persona.persona_id = ?;";
	
						
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
	
	//OBTENER
	
	public int obtenerId(Persona persona) throws SQLException
	{
		int retorno = -1;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Persona WHERE Persona.dni = ? " ;
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setInt(1, persona.dni());
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								retorno = resultado.getInt("persona_id");
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
	
	public boolean existe(int idPersona) throws SQLException
	{
		boolean retorno = false;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Persona WHERE Persona.persona_id = ? " ;
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setInt(1, idPersona);
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								retorno = true;
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
