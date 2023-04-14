package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionVehiculo 
{
	ConexionMySql conexion;
	Connection connection;
	PreparedStatement preStatement;
	
	public ConexionVehiculo()
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
	
	public void insertar(Vehiculo vehiculo) throws SQLException
	{
		try
		{
			this.conectar();		
			
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO Vehiculo (dominio, marca_id, nombre_propietario, vehiculo_id_persona) VALUES (?, ?, ?, ?)";

					
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setString(1, vehiculo.dominio());
						preStatement.setInt(2, vehiculo.marca().id());
						preStatement.setString(3, vehiculo.nombre_propietario());
						preStatement.setInt(4, vehiculo.id_propietario());
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
	
	public void modificar(Vehiculo vehiculo) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						String consulta = "UPDATE Vehiculo SET dominio = ?, marca_id = ?, nombre_propietario = ?, vehiculo_id_persona = ? WHERE Vehiculo.vehiculo_id = ?;";
						
						
						this.preStatement = this.connection.prepareStatement(consulta);
						preStatement.setString(1, vehiculo.dominio());
						preStatement.setInt(2, vehiculo.marca().id());
						preStatement.setString(3, vehiculo.nombre_propietario());
						preStatement.setInt(4, vehiculo.id_propietario());
						preStatement.setInt(5, vehiculo.id());
						
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
	
	public void eliminarVehiculo(int id) throws SQLException
	{
			try
			{
				
				ConexioAuto conexionAuto = new ConexioAuto();
				
					if(conexionAuto.existe(id))
					{
						conexionAuto.eliminarAuto(id);
					}
				
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						String consulta = "DELETE FROM Vehiculo WHERE Vehiculo.vehiculo_id = ?;";
				
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
	
	public ListaVehiculos obtenerLista() throws SQLException
	{
		ListaVehiculos retorno = new ListaVehiculos();
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Vehiculo INNER JOIN Marca ON Marca.marca_id = Vehiculo.marca_id INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								//System.out.print("ID Persona: " + resultado.getInt("vehiculo_id_persona"));
								retorno.agregar
								( 
									new Auto
									(
										resultado.getInt("vehiculo_id"),
										resultado.getString("dominio"),
										new Marca(resultado.getInt("marca_id"), resultado.getString("marca"), new Modelo(resultado.getInt("modelo_id"), resultado.getString("modelo"))),
										resultado.getString("nombre_propietario"),
										resultado.getInt("vehiculo_id_persona")
									)
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
	
	public Vehiculo obtener(int id) throws SQLException
	{
		Vehiculo retorno = null;
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Vehiculo INNER JOIN Marca ON Marca.marca_id = Vehiculo.marca_id INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id WHERE Vehiculo.vehiculo_id = ?";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, id);
							
	
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								//System.out.print("ID Persona: " + resultado.getInt("vehiculo_id_persona"));
								retorno = new Auto
									(
										resultado.getInt("vehiculo_id"),
										resultado.getString("dominio"),
										new Marca(resultado.getInt("marca_id"), resultado.getString("marca"), new Modelo(resultado.getInt("modelo_id"), resultado.getString("modelo"))),
										resultado.getString("nombre_propietario"),
										resultado.getInt("vehiculo_id_persona")
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
	
	public int obtener_id(String dominio) throws SQLException
	{
		int retorno = -1;
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Vehiculo INNER JOIN Marca ON Marca.marca_id = Vehiculo.marca_id INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id WHERE Vehiculo.dominio = ?";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setString(1, dominio);
	
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								retorno = resultado.getInt("vehiculo_id");

										
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
	
	public boolean existe(int id) throws SQLException
	{
		boolean retorno = false;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Vehiculo WHERE vehiculo_id = ? " ;
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setInt(1, id);
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
