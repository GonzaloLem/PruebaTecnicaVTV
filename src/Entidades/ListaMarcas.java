package Entidades;

import java.util.ArrayList;
import java.util.List;

public class ListaMarcas 
{
	private List<Marca> lista_personas;
	
	
	public ListaMarcas()
	{
		this.lista_personas = new ArrayList<Marca>();
	}
	
	public List<Marca> lista()
	{
		return this.lista_personas;
	}
	
	public int size()
	{
		return this.lista_personas.size();
	}
	
	public int buscar(Marca marca)
	{
		int retorno = -1;
		
			for(int i=0;i<this.lista().size();i++)
			{
				if(this.lista_personas.get(i).equals(marca))
				{
					retorno = i;
					break;
				}
			}
		
		return retorno;
	}
	
	public void agregar(Marca marca)
	{
		if(marca != null && this.buscar(marca) == -1)
		{
			this.lista_personas.add(marca);
		}
	}
	
	public void modificar(Marca marca)
	{
		if(this.buscar(marca) != -1)
		{
			this.lista_personas.set(this.buscar(marca), marca);
		}
	}
	
	public void eliminar(Marca marca)
	{
		if(this.buscar(marca) != -1)
		{
			this.lista_personas.remove(this.buscar(marca));
		}
	}
	
	public Marca obtener(int indice)
	{
		return this.lista_personas.get(indice);
	}
}
