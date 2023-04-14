package Entidades;

public class Inspector extends Persona
{
	private double inspector_celular;
	private int inspector_inspecciones_realizadas;
	
	//Constructor
	public Inspector(String nombre, String apellido, int edad, int dni, double celular)
	{
		super(nombre, apellido, edad, dni);
		
		this.inspector_celular = celular;
		this.inspector_inspecciones_realizadas = 0;
	}
	
	public Inspector(int id, String nombre, String apellido, int edad, int dni, double celular, int inspecciones)
	{
		super(id, nombre, apellido, edad, dni);
		
		this.inspector_celular = celular;
		this.inspector_inspecciones_realizadas = inspecciones;
	}
	
	//Getters
	public double celular()
	{
		return this.inspector_celular;
	}

	public int inspecciones()
	{
		return this.inspector_inspecciones_realizadas;
	}
	
	//Metodos
	public void sumar_inspeccion()
	{
		this.inspector_inspecciones_realizadas += 1;
	}
	
	
}
