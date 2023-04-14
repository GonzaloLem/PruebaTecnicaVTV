package Entidades;
//import java.text.SimpleDateFormat;

public class Inspeccion 
{
	private int inspeccion_numero;
	private String inspeccion_fecha;
	private String inspeccion_estado;
	private boolean inspeccion_exento;
	private Vehiculo inspeccion_vehiculo;
	private int inspeccion_id_inspector;
	private Observacion observacion;
	private VerificacionTecnicaVehicular vtv;
	
	//Constructor
	
	public Inspeccion(String fecha, String estado, boolean exento, Vehiculo vehiculo, int id_inspector)
	{
		this.inspeccion_numero = 0;
		this.inspeccion_fecha = fecha;
		this.inspeccion_estado = estado;
		this.inspeccion_exento = exento;
		this.inspeccion_vehiculo = vehiculo;
		this.inspeccion_id_inspector = id_inspector;
	}
	
	public Inspeccion(int numero, String fecha, String estado, boolean exento, Vehiculo vehiculo, int id_inspector)
	{
		this(fecha, estado, exento, vehiculo, id_inspector);
		this.inspeccion_numero = numero;

	}
	
	public Inspeccion(int numeroInspeccion, String fecha, String estado, boolean exento, Vehiculo vehiculo, int id_inspector, Observacion observacion, VerificacionTecnicaVehicular vtv)
	{
		this(fecha, estado, exento, vehiculo, id_inspector);
		
		this.inspeccion_numero = numeroInspeccion;
		this.observacion = observacion;
		this.vtv = vtv;
		
	}
	
	
	//Getters
	
	
	public int numero()
	{
		return this.inspeccion_numero;
	}
	
	public String fecha()
	{
		return this.inspeccion_fecha;
	}
	
	public String estado()
	{
		return this.inspeccion_estado;
	}

	public boolean exento()
	{
		return this.inspeccion_exento;
	}
	
	public Vehiculo vehiculo()
	{
		return this.inspeccion_vehiculo;
	}
	
	public int id_inspector()
	{
		return this.inspeccion_id_inspector;
	}
	
	public Observacion inspeccion_observacion()
	{
		return this.observacion;
	}
	
	public VerificacionTecnicaVehicular inspeccion_vtv()
	{
		return this.vtv;
	}
	
	//Equals
	
	private boolean comparacion(Inspeccion inspeccion)
	{
		return (this.inspeccion_numero == inspeccion.numero());
	}
	
	public boolean equals(Object obj)
	{
		boolean retorno = false;
		
			if(obj instanceof Inspeccion)
			{
				retorno = this.comparacion((Inspeccion)obj);
			}
		
		return retorno;
	}
	
}
