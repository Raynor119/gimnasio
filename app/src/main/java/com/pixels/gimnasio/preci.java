package com.pixels.gimnasio;

public class preci
{
	private String Cod,Nombre,Dias,Precio;

	public preci(){

	}

	public preci(String cod,String nombre,String dias,String precio){
		this.Cod=cod;
		this.Nombre=nombre;
		this.Dias=dias;
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
	public String getDias(){
		return Dias;
	}
	public String getPrecio(){
		return Precio;
	}
	
}
