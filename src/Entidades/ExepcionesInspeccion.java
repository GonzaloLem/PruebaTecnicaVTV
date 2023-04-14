package Entidades;

import java.sql.SQLException;

public class ExepcionesInspeccion extends Exception
{

	private static final long serialVersionUID = 1L;

	public ExepcionesInspeccion()
	{
		super();
	}
	
	public ExepcionesInspeccion(String cadena)
	{
		super(cadena);
	}
	
	public void validarIdVehiculo(int id) throws SQLException, ExepcionesInspeccion
	{
		ConexionVehiculo vehiculo = new ConexionVehiculo();
		
		if(!vehiculo.existe(id))
		{
			throw new ExepcionesInspeccion("Porfavor ingrese un ID de Vehiculo Valido");
		}
		
		
	}
	
	public void validarIdInspector(int id) throws SQLException, ExepcionesInspeccion
	{
		ConexionInspector inspector = new ConexionInspector();
		
		if(!inspector.existe(id))
		{
			throw new ExepcionesInspeccion("El Inspector no Existe!");
		}
		
		
	}
	
	public void validarVTVAuto(int id) throws ExepcionesInspeccion, SQLException
	{
		ConexionInspeccion conexionInspeccion = new ConexionInspeccion();
		
		if(conexionInspeccion.sabesEstadoAuto(id))
		{
			throw new ExepcionesInspeccion("El Vehiculo ya teniene la vtv Aprobada");
		}
	}
	
}
