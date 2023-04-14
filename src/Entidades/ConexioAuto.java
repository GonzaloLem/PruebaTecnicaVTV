package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConexioAuto extends ConexionVehiculo
{

	public ConexioAuto()
	{
		
	}
	
	public void insertar(Auto auto) throws SQLException
	{
		try
		{
			
			//this.insertar((Vehiculo)auto);
			ConexionVTV conexionVTV = new ConexionVTV();
			int idVTV = conexionVTV.obtener_id(this.obtener_id(auto.dominio()));
			int idAuto = this.obtener_id(auto.dominio());
			
			this.conectar();				
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO Auto (auto_id, auto_vtv_id) VALUES (?, ?)";			
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setInt(1, idAuto);
						preStatement.setInt(2, idVTV);
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
	
	public void modificar(Auto auto) throws SQLException
	{
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						String consulta = "";
						
						
						this.preStatement = this.connection.prepareStatement(consulta);

						
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
	
	public void eliminarAuto(int id) throws SQLException
	{
			try
			{
				//this.eliminar_ids_vtv(this.obtener_ids_vtv(id));
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						String consulta = "DELETE FROM Auto WHERE Auto.auto_id = ?;";
				
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
	
	public void eliminar_ids_vtv(List<Integer> lista)
	{
		ConexionVTV conexion = new ConexionVTV();
		for(int i=0;i<lista.size();i++)
		{
			try {
				conexion.eliminarVTV(lista.get(i));
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
		}
	}
	
	
	@Override
	public boolean existe(int id) throws SQLException
	{
		boolean retorno = false;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Auto WHERE Auto.auto_id = ? " ;
	
						
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
	
	public List<Integer> obtener_ids_vtv(int id) throws SQLException
	{
		List<Integer> retorno = new ArrayList<Integer>();
		
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Auto WHERE Auto.auto_id = ?;";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, id);
							
	
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								//System.out.print("ID Persona: " + resultado.getInt("vehiculo_id_persona"));
								retorno.add(resultado.getInt("auto_vtv_id")) ;
			
										
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
