package Entidades;


public class Validar 
{
	public static boolean soloEnteros(String cadena)
	{
		boolean retorno = false;
		try
		{
			Integer.parseInt(cadena);
			retorno = true;
		}
		catch(NumberFormatException e)
		{
			
		}
		
		return retorno;
	}
	
	public static boolean soloDobles(String cadena)
	{
		boolean retorno = false;
		try
		{
			Double.parseDouble(cadena);
			retorno = true;
		}
		catch(NumberFormatException e)
		{
			
		}
		
		return retorno;
	}
	
}
