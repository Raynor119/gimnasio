package com.pixels.gimnasio;


public class descu
{
	private String Cod,Nombre,Precio;

	public descu(){

	}

	public descu(String cod,String nombre,String precio){
		this.Cod=cod;
		this.Nombre=nombre;
		
		this.Precio=precio;
	}

	public String getCod(){
		return Cod;
	}
	public void setCod(String cod){
		Cod=cod;
	}

	public String getNombre(){
		return Nombre;
	}
	public void setNombre(String nombre){
		Nombre=nombre;
	}
	
	public String getPrecio(){
		return Precio;
	}

}
