package Entidades;

public class VerificacionTecnicaVehicular 
{
	private int vtv_id;
	private Observacion vtv_observaciones;
	private String vtv_mediciones;
	private String vtv_estado;
	
	public VerificacionTecnicaVehicular()
	{
		this.vtv_observaciones = null;
		this.vtv_mediciones = null;
		this.vtv_estado = null;
	}
	
	public VerificacionTecnicaVehicular(Observacion observacion, String mediciones, String estado)
	{
		this.vtv_observaciones = observacion;
		this.vtv_mediciones = mediciones;
		this.vtv_estado = estado;
	}
	
	public VerificacionTecnicaVehicular(int id, Observacion observacion, String mediciones, String estado)
	{
		this(observacion, mediciones, estado);
		this.vtv_id = id;
	}
	
	public int vtv_id()
	{
		return this.vtv_id;
	}
	
	public Observacion observacion()
	{
		return this.vtv_observaciones;
	}
	
	public String mediciones()
	{
		return this.vtv_mediciones;
	}
	
	public String estado()
	{
		return this.vtv_estado;
	}
	
	
	public boolean exento()
	{
		boolean retorno = false;
		
		if(this.vtv_estado == "Condicional")
		{
			retorno = true;
		}
		
		return retorno;
	}
	
}
