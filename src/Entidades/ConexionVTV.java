package Entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionVTV 
{
	ConexionMySql conexion;
	Connection connection;
	PreparedStatement preStatement;
	
	public ConexionVTV()
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
	
	public void insertar(VerificacionTecnicaVehicular vtv, int idAuto) throws SQLException
	{
		try
		{
			ConexionObservaciones conexionObservacion = new ConexionObservaciones();
			int id_observacion = conexionObservacion.obtener_idMaximo();
			
			this.conectar();		
			
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO vtv (vtv_observaciones_id, mediciones, estado, vtv_id_auto) VALUES (?, ?, ?, ?)";

					
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setInt(1, id_observacion);
						preStatement.setString(2, vtv.mediciones());
						preStatement.setString(3, vtv.estado());
						preStatement.setInt(4, idAuto);
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
	
	public void modificar(VerificacionTecnicaVehicular vtv, int idAuto) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						String consulta = "UPDATE vtv SET vtv_observaciones_id = ?, mediciones = ?, estado = ?, vtv_id_auto = ? WHERE vtv.vtv_id = ?;";
						
						
						this.preStatement = this.connection.prepareStatement(consulta);
						preStatement.setInt(1, vtv.observacion().id());
						preStatement.setString(2, vtv.mediciones());
						preStatement.setString(3, vtv.estado());
						preStatement.setInt(4, idAuto);
						preStatement.setInt(5, vtv.vtv_id());
						
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
	
	public void eliminarVTV(int id) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						String consulta = "DELETE FROM vtv WHERE vtv.vtv_id = ?;";
				
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
	//SELECT MAX(vtv_id) FROM vtv where vtv.vtv_id_auto = 1;
	
	public int obtener_id(int idAuto) throws SQLException
	{
		int retorno = -1;
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT MAX(vtv_id) FROM vtv where vtv.vtv_id_auto = ?";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, idAuto);
							
	
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								//System.out.print("ID Persona: " + resultado.getInt("vehiculo_id_persona"));
								retorno = resultado.getInt("MAX(vtv_id)");										
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
	
	public VerificacionTecnicaVehicular obtener(int id) throws SQLException
	{
		VerificacionTecnicaVehicular retorno = null;
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM vtv INNER JOIN Observacion ON vtv.vtv_observaciones_id = Observacion.observacion_id WHERE vtv.vtv_id;";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, id);
							
	
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								retorno = new VerificacionTecnicaVehicular
									(
										resultado.getInt("vtv_id"),
										new Observacion(resultado.getInt("observacion_id"), resultado.getString("detalle"), resultado.getString("aprobacion"), resultado.getInt("id_inspector")),
										resultado.getString("mediciones"),
										resultado.getString("estado")
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
	
}
