package Entidades;

public class Auto extends Vehiculo
{

	private VerificacionTecnicaVehicular auto_vtv;
	
	public Auto(String dominio, Marca marca, String nombre_propietario, int id_propietario)
	{
		super(dominio, marca,nombre_propietario, id_propietario);
		
		this.auto_vtv = null;
	}
	
	public Auto(int id, String dominio, Marca marca, String nombre_propietario, int id_propietario)
	{
		super(id, dominio, marca,nombre_propietario, id_propietario);
		
		this.auto_vtv = null;
	}
	
	public Auto(int id, String dominio, Marca marca, String nombre_propietario, int id_propietario, VerificacionTecnicaVehicular vtv)
	{
		super(id, dominio, marca, nombre_propietario, id_propietario);
		
		this.auto_vtv = vtv;
	}
	
	public VerificacionTecnicaVehicular vtv()
	{
		return this.auto_vtv;
	}
	
	
	// metodos
	/*
	private boolean analizar_mediciones()
	{
		boolean retorno = false;
				
		int numero = (int)(Math.random()*15+1);
		
			if(numero > 4)
			{
				retorno = true;
			}
		return retorno;
	}
	
	
	private boolean analizar_vtv()
	{
		boolean retorno = false;
		
		if(this.auto_observaciones.aprobacion() == true && this.auto_mediciones == true)
		{
			this.auto_vtv = true;
		}
		
		return retorno;
	}
	*/
	
	/*public String informar_vtv()
	{
		return this.auto_vtv ? "Aprobado\n" + this.auto_observaciones.detalle() : "Desaprobado\n" + this.auto_observaciones.detalle();
	}*/
	
}
