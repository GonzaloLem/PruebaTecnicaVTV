package Entidades;

public class Modelo 
{

	private int modelo_id;
	private String tipo_modelo;
	
	public Modelo(int id, String modelo)
	{
		this.modelo_id = id;
		this.tipo_modelo = modelo;
	}
	
	public int id()
	{
		return this.modelo_id;
	}
	
	public String modelo()
	{
		return this.tipo_modelo;
	}
	
}
