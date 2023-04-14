package Entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionCliente extends ConexionPersona
{
	public ConexionCliente()
	{
		

	}
	
	public void insertarCliente(Cliente cliente) throws SQLException
	{
		try
		{
			this.insertarPersona(cliente);
			int id = this.obtenerId(cliente);
					
			this.conectar();	
				if(this.conexion.conectar())
				{
					String consulta = "INSERT INTO Cliente (id_cliente, email) VALUES (?,?)";

						this.preStatement = this.connection.prepareStatement(consulta);
						
						preStatement.setInt(1, id);
						preStatement.setString(2, cliente.email());
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
	
	public ListaPersonas obtenercliente() throws SQLException
	{
		ListaPersonas retorno = new ListaPersonas();
			try
			{
				this.conectar();		
				
					if(this.conexion.conectar())
					{
						ResultSet resultado = null;
						String consulta = "SELECT * FROM Persona INNER JOIN Cliente ON Persona.persona_id = Cliente.id_cliente";
	
						
							this.preStatement = this.connection.prepareStatement(consulta);
							
							resultado = preStatement.executeQuery();
							
							
							while(resultado.next())
							{
								
								 retorno.agregar
								 (
										 new Cliente
										 (
												resultado.getInt("persona_id"), 
												resultado.getString("nombre"),
												resultado.getString("apellido"),
												resultado.getInt("edad"),
												resultado.getInt("dni"), 
												resultado.getString("email")
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
	
}
