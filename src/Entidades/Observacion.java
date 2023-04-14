package Entidades;

public class Observacion 
{
	private int observacion_id;
	private String observacion_detalle;
	private String observacion_aprobacion;
	private int observacion_id_inspector;
	
	public Observacion(String detalle, String aprobacion, int id_inspector)
	{
		this.observacion_detalle = detalle;
		this.observacion_aprobacion = aprobacion;
		this.observacion_id_inspector = id_inspector;
		
		this.observacion_id = 0;
	}
	
	public Observacion(int id, String detalle, String aprobacion, int id_inspector)
	{
		this(detalle, aprobacion, id_inspector);
		
		this.observacion_id = id;
	}
	
	public int id()
	{
		return this.observacion_id;
	}
	
	public String detalle()
	{
		return this.observacion_detalle;
	}
	
	public String aprobacion()
	{
		return this.observacion_aprobacion;
	}
	
	public int id_inspector()
	{
		return this.observacion_id_inspector;
	}
	
}
