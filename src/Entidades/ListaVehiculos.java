package Entidades;

import java.util.ArrayList;
import java.util.List;

public class ListaVehiculos 
{
	private List<Vehiculo> lista_vehiculos;
	
	
	public ListaVehiculos()
	{
		this.lista_vehiculos = new ArrayList<Vehiculo>();
	}
	
	public List<Vehiculo> lista()
	{
		return this.lista_vehiculos;
	}
	
	public int size()
	{
		return this.lista_vehiculos.size();
	}
	
	public int buscar(Vehiculo vehiculo)
	{
		int retorno = -1;
		
			for(int i=0;i<this.lista().size();i++)
			{
				if(this.lista_vehiculos.get(i).equals(vehiculo))
				{
					retorno = i;
					break;
				}
			}
		
		return retorno;
	}
	
	public void agregar(Vehiculo vehiculo)
	{
		if(vehiculo != null && this.buscar(vehiculo) == -1)
		{
			this.lista_vehiculos.add(vehiculo);
		}
	}
	
	public void modificar(Vehiculo vehiculo)
	{
		if(this.buscar(vehiculo) != -1)
		{
			this.lista_vehiculos.set(this.buscar(vehiculo), vehiculo);
		}
	}
	
	public void eliminar(Vehiculo vehiculo)
	{
		if(this.buscar(vehiculo) != -1)
		{
			this.lista_vehiculos.remove(this.buscar(vehiculo));
		}
	}
	
	public Vehiculo obtener(int indice)
	{
		return this.lista_vehiculos.get(indice);
	}
}
