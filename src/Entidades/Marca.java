package Entidades;

public class Marca 
{
	private int marca_id;
	private String tipo_marca;
	private Modelo tipo_modelo;
	
	public Marca(int id, String marca, Modelo modelo)
	{
		this.marca_id = id;
		this.tipo_marca = marca;
		this.tipo_modelo = modelo;
	}
	
	public int id()
	{
		return this.marca_id;
	}
	
	public String marca()
	{
		return this.tipo_marca;
	}
	
	public Modelo modelo()
	{
		return this.tipo_modelo;
	}
	
}
