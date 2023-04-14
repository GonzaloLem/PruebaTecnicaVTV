package Entidades;

import java.util.ArrayList;
import java.util.List;

public class ListaInspecciones 
{
	private List<Inspeccion> lista_inspeccion;
	
	
	public ListaInspecciones()
	{
		this.lista_inspeccion = new ArrayList<Inspeccion>();
	}
	
	public List<Inspeccion> lista()
	{
		return this.lista_inspeccion;
	}
	
	public int size()
	{
		return this.lista_inspeccion.size();
	}
	
	public int buscar(Inspeccion inspeccion)
	{
		int retorno = -1;
		
			for(int i=0;i<this.lista().size();i++)
			{
				if(this.lista_inspeccion.get(i).equals(inspeccion))
				{
					retorno = i;
					break;
				}
			}
		
		return retorno;
	}
	
	public void agregar(Inspeccion inspeccion)
	{
		if(inspeccion != null && this.buscar(inspeccion) == -1)
		{
			this.lista_inspeccion.add(inspeccion);
		}
	}
	
	public void modificar(Inspeccion inspeccion)
	{
		if(this.buscar(inspeccion) != -1)
		{
			this.lista_inspeccion.set(this.buscar(inspeccion), inspeccion);
		}
	}
	
	public void eliminar(Inspeccion inspeccion)
	{
		if(this.buscar(inspeccion) != -1)
		{
			this.lista_inspeccion.remove(this.buscar(inspeccion));
		}
	}
	
	public Inspeccion obtener(int indice)
	{
		return this.lista_inspeccion.get(indice);
	}
}
