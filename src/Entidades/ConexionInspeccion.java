package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class ConexionInspeccion 
{

	ConexionMySql conexion;
	Connection connection;
	PreparedStatement preStatement;
	
	public ConexionInspeccion()
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
	
	public void insertar(Inspeccion inspeccion, Vehiculo vehiculo) throws SQLException
	{
		try
		{			
			ConexionVTV conexionVTV = new ConexionVTV();
			ConexionVehiculo conexionVehiculo = new ConexionVehiculo();
			
			int idVTV = conexionVTV.obtener_id(conexionVehiculo.obtener_id(vehiculo.dominio()));
			
			
			
			this.conectar();		
			
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO Inspeccion (fecha, estado, exento, inspeccion_vehiculo_id, inspeccion_inspector_id, inspeccion_vtv_id) VALUES (?, ?, ?, ?, ?, ?)";

					
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setString(1,inspeccion.fecha().toString());
						preStatement.setString(2, inspeccion.estado());
						preStatement.setBoolean(3, inspeccion.exento());
						preStatement.setInt(4, inspeccion.vehiculo().id());
						preStatement.setInt(5, inspeccion.id_inspector());
						preStatement.setInt(6, idVTV);
						preStatement.execute();
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
	
	public void modificar(Inspeccion inspeccion) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						String consulta = "UPDATE Inspeccion SET fecha = ?, mediciones = ?, estado = ?, exento = ?, inspeccion_vehiculo_id = ?, inspeccion_inspector_id = ?, inspeccion_vtv_id = ? WHERE Inspeccion.inspeccion_numero = ?;";
						
						
						this.preStatement = this.connection.prepareStatement(consulta);
						preStatement.setString(1, inspeccion.fecha());
						preStatement.setString(2, inspeccion.inspeccion_vtv().mediciones());
						preStatement.setString(3, inspeccion.estado());
						preStatement.setBoolean(4, inspeccion.exento());
						preStatement.setInt(5, inspeccion.vehiculo().id());
						preStatement.setInt(6, inspeccion.id_inspector());
						preStatement.setInt(7, inspeccion.inspeccion_vtv().vtv_id());
						preStatement.setInt(8, inspeccion.numero());
						
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
	
	public void eliminar(int numero) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						String consulta = "DELETE FROM Inspeccion WHERE Inspeccion.inspeccion_numero = ?;";
				
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, numero);
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
	
	public ListaInspecciones obtenerLista() throws SQLException
	{
		ListaInspecciones retorno = new ListaInspecciones();
			
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Inspeccion INNER JOIN vtv ON Inspeccion.inspeccion_vtv_id = vtv_id INNER JOIN Observacion ON Observacion.observacion_id = vtv.vtv_observaciones_id INNER JOIN Vehiculo ON Inspeccion.inspeccion_vehiculo_id = Vehiculo.vehiculo_id INNER JOIN Marca ON Marca.marca_id = Vehiculo.marca_id INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno.agregar
								 (
										 new Inspeccion
										 (
												resultado.getInt("inspeccion_numero"), 
												resultado.getString("fecha"),
												resultado.getString("estado"),
												resultado.getBoolean("exento"),
														new Auto(										
																resultado.getInt("vehiculo_id"),
																resultado.getString("dominio"),
																	new Marca(resultado.getInt("marca_id"), resultado.getString("marca"), new Modelo(resultado.getInt("modelo_id"), resultado.getString("modelo"))),
																resultado.getString("nombre_propietario"),
																resultado.getInt("vehiculo_id_persona")
														), 
												resultado.getInt("inspeccion_inspector_id"),
												new Observacion(resultado.getInt("observacion_id"), resultado.getString("detalle"), resultado.getString("aprobacion"), resultado.getInt("id_inspector")),
														new VerificacionTecnicaVehicular
														(
																resultado.getInt("vtv_id"),
																new Observacion(resultado.getInt("observacion_id"), resultado.getString("detalle"), resultado.getString("aprobacion"), resultado.getInt("id_inspector")),
																resultado.getString("mediciones"),
																resultado.getString("estado")
														)
										 )
								 );
							}
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
		
		return retorno;

	}
	
	
	//SELECT MAX(inspeccion_numero) FROM Inspeccion where inspeccion_vehiculo_id = 5 ;
	
	public Inspeccion obtenerInspeccion(int numeroInspeccion) throws SQLException
	{
		Inspeccion retorno = null;
			
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Inspeccion INNER JOIN vtv ON Inspeccion.inspeccion_vtv_id = vtv_id INNER JOIN Observacion ON Observacion.observacion_id = vtv.vtv_observaciones_id INNER JOIN Vehiculo ON Inspeccion.inspeccion_vehiculo_id = Vehiculo.vehiculo_id INNER JOIN Marca ON Marca.marca_id = Vehiculo.marca_id INNER JOIN Modelo ON Marca.id_tipo_modelo = Modelo.modelo_id WHERE Inspeccion.inspeccion_numero = ? ";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setInt(1, numeroInspeccion);
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno = new Inspeccion
										 (
												resultado.getInt("inspeccion_numero"), 
												resultado.getString("fecha"),
												resultado.getString("estado"),
												resultado.getBoolean("exento"),
														new Auto(										
																resultado.getInt("vehiculo_id"),
																resultado.getString("dominio"),
																	new Marca(resultado.getInt("marca_id"), resultado.getString("marca"), new Modelo(resultado.getInt("modelo_id"), resultado.getString("modelo"))),
																resultado.getString("nombre_propietario"),
																resultado.getInt("vehiculo_id_persona")
														), 
												resultado.getInt("inspeccion_inspector_id"),
												new Observacion(resultado.getInt("observacion_id"), resultado.getString("detalle"), resultado.getString("aprobacion"), resultado.getInt("id_inspector")),
														new VerificacionTecnicaVehicular
														(
																resultado.getInt("vtv_id"),
																new Observacion(resultado.getInt("observacion_id"), resultado.getString("detalle"), resultado.getString("aprobacion"), resultado.getInt("id_inspector")),
																resultado.getString("mediciones"),
																resultado.getString("estado")
														)
										 );
								 
							}
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
		
		return retorno;

	}
	
	public boolean sabesEstadoAuto(int idAuto) throws SQLException
	{
		boolean retorno = false;
			
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Inspeccion where inspeccion_vehiculo_id = ?;";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setInt(1, idAuto);
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								if(resultado.getString("estado").length() == "Apto".length())
								{
									retorno = true;
								}
								 
							}
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
		
		return retorno;

	}
	
	
}
