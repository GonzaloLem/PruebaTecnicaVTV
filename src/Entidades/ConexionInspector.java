package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionInspector extends ConexionPersona
{
	//ConexionMySql conexion;
	//Connection connection;
	//PreparedStatement preStatement;
	
	public ConexionInspector()
	{
	

	}
	
	/*private void conectar()
	{
		this.conexion = new ConexionMySql();
		
			if(this.conexion.conectar())
			{
				this.connection = this.conexion.obtenerConexion();
			}
		
	}*/
	
	public void insertarInspector(Inspector inspector) throws SQLException
	{
		try
		{
			this.insertarPersona(inspector);
			int id = this.obtenerId(inspector);		
			
			this.conectar();
				if(this.conexion.conectar())
				{	
					
					String consulta = "INSERT INTO Inspector (id_inspector, celular, inspecciones_realizadas) VALUES (?,?,?)";

					
						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setInt(1, id);
						preStatement.setDouble(2, inspector.celular());
						preStatement.setInt(3, inspector.inspecciones());
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
	
	//MODIFICAR
	public void modificar(Inspector inspector) throws SQLException
	{
			try
			{
				this.modificar((Persona)inspector);
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						
						String consulta = "UPDATE Inspector SET celular = ?, inspecciones_realizadas = ? WHERE Inspector.id_inspector = ?;";
						
						
						this.preStatement = this.connection.prepareStatement(consulta);
						preStatement.setDouble(1, inspector.celular());
						preStatement.setInt(2, inspector.inspecciones());
						preStatement.setInt(3, inspector.id());
						
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
						String consulta = "DELETE FROM Inspector WHERE Inspector.id_inspector = ?;";
				
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
	
	// OBTENER
	public ListaPersonas obtenerInspectores() throws SQLException
	{
		ListaPersonas retorno = new ListaPersonas();
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Persona INNER JOIN Inspector ON Persona.persona_id = Inspector.id_inspector";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno.agregar
								 (
										 new Inspector
										 (
												resultado.getInt("persona_id"), 
												resultado.getString("nombre"),
												resultado.getString("apellido"),
												resultado.getInt("edad"),
												resultado.getInt("dni"), 
												resultado.getDouble("celular"),
												resultado.getInt("inspecciones_realizadas")
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
	
	public Inspector obtener_inspector(int id) throws SQLException
	{
		Inspector retorno = null;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Persona INNER JOIN Inspector ON Persona.persona_id = Inspector.id_inspector WHERE Persona.persona_id = ?";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							preStatement.setInt(1, id);
							
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno = new Inspector
										 (
												resultado.getInt("persona_id"), 
												resultado.getString("nombre"),
												resultado.getString("apellido"),
												resultado.getInt("edad"),
												resultado.getInt("dni"), 
												resultado.getDouble("celular"),
												resultado.getInt("inspecciones_realizadas")
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
	
	@Override
	public boolean existe(int idPersona) throws SQLException
	{
		boolean retorno = false;
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Inspector WHERE Inspector.id_inspector = ? " ;
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							preStatement.setInt(1, idPersona);
							resultado = preStatement.executeQuery();
							
							
							if(resultado.next())
							{
								retorno = true;
								//System.out.print("Paso!!!!!");
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
