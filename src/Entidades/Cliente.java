package Entidades;

public class Cliente extends Persona
{
	
	private String cliente_email;
	
	public Cliente(String nombre, String apellido, int edad, int dni, String email)
	{
		super(nombre, apellido, edad, dni);
		
		this.cliente_email = email;
	}
	
	public Cliente(int id, String nombre, String apellido, int edad, int dni, String email)
	{
		super(id, nombre, apellido, edad, dni);
		
		this.cliente_email = email;
	}
	
	public String email()
	{
		return this.cliente_email;
	}
	
}
