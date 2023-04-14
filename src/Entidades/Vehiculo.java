package Entidades;

public abstract class Vehiculo 
{
	private int vehiculo_id;
	private String vehiculo_dominio;
	private Marca vehiculo_marca;
	private String vehiculo_nombre_propietario;
	private int vehiculo_id_propietario;
	
	//Constructores
	
	public Vehiculo(String dominio, Marca marca, String nombre_propietario, int id_propietario)
	{
		this.vehiculo_id = 0;
		this.vehiculo_dominio = dominio;
		this.vehiculo_marca = marca;
		this.vehiculo_nombre_propietario = nombre_propietario;
		this.vehiculo_id_propietario = id_propietario;
	}
	
	public Vehiculo(int id, String dominio, Marca marca, String nombre_propietario, int id_propietario)
	{
		this(dominio, marca, nombre_propietario, id_propietario);
		this.vehiculo_id = id;
	}
	
	//Getters
	
	public int id()
	{
		return this.vehiculo_id;
	}
	
	public String dominio()
	{
		return this.vehiculo_dominio;
	}
	
	public Marca marca()
	{
		return this.vehiculo_marca;
	}
	
	
	public String nombre_propietario()
	{
		return this.vehiculo_nombre_propietario;
	}
	
	public int id_propietario()
	{
		return this.vehiculo_id_propietario;
	}
	
	//Metodos
	
	public String informacion()
	{
		return "[Dominio] - " + this.vehiculo_dominio + "\n" +
				"[Marca] - " + this.vehiculo_marca + "\n" + 
				"[Modelo] - " + this.vehiculo_marca.modelo().modelo() + "\n" + 
				"[Propietario] - " + this.vehiculo_nombre_propietario + "\n";
	}
	
	//Equals
	
	private boolean comparacion(Vehiculo vehiculo)
	{
		return (this.vehiculo_dominio == vehiculo.dominio());
	}
	
	public boolean equals(Object obj)
	{
		boolean retorno = false;
		
			if(obj instanceof Vehiculo)
			{
				retorno = this.comparacion((Vehiculo)obj);
			}
		
		return retorno;
	}
	
}
