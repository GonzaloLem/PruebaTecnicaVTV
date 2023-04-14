package Entidades;
import java.util.List;
import java.util.ArrayList;

public class ListaPersonas 
{
	private List<Persona> lista_personas;
	
	
	public ListaPersonas()
	{
		this.lista_personas = new ArrayList<Persona>();
	}
	
	public List<Persona> lista()
	{
		return this.lista_personas;
	}
	
	public int size()
	{
		return this.lista_personas.size();
	}
	
	public int buscar(Persona persona)
	{
		int retorno = -1;
		
			for(int i=0;i<this.lista().size();i++)
			{
				if(this.lista_personas.get(i).equals(persona))
				{
					retorno = i;
					break;
				}
			}
		
		return retorno;
	}
	
	public void agregar(Persona persona)
	{
		if(persona != null && this.buscar(persona) == -1)
		{
			this.lista_personas.add(persona);
		}
	}
	
	public void modificar(Persona persona)
	{
		if(this.buscar(persona) != -1)
		{
			this.lista_personas.set(this.buscar(persona), persona);
		}
	}
	
	public void eliminar(Persona persona)
	{
		if(this.buscar(persona) != -1)
		{
			this.lista_personas.remove(this.buscar(persona));
		}
	}
	
	public Persona obtener(int indice)
	{
		return this.lista_personas.get(indice);
	}
	
}
