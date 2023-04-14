package Entidades;

public abstract class Persona 
{

	private int persona_id;
	private String persona_nombre;
	private String persona_apellido;
	private int persona_edad;
	private int persona_dni;
	
	// Constructor
	public Persona(String nombre, String apellido, int edad, int dni)
	{
		this.persona_id = 0;
		this.persona_nombre = nombre;
		this.persona_apellido = apellido;
		this.persona_edad = edad;
		this.persona_dni = dni;
	}
	
	public Persona(int id, String nombre, String apellido, int edad, int dni)
	{
		this(nombre, apellido, edad, dni);
		
		this.persona_id = id;
	}
	
	// Getters
	public int id()
	{
		return this.persona_id;
	}

	public String nombre()
	{
		return this.persona_nombre;
	}
	
	public String apellido()
	{
		return this.persona_apellido;
	}
	
	public int edad()
	{
		return this.persona_edad;
	}
	
	public int dni()
	{
		return this.persona_dni;
	}
	
	//Equals
	
	private boolean comparacion(Persona persona)
	{
		return (this.persona_dni == persona.dni());
	}
	
	public boolean equals(Object obj)
	{
		boolean retorno = false;
		
			if(obj instanceof Persona)
			{
				retorno = this.comparacion((Persona)obj);
			}
		
		return retorno;
	}
	
}
