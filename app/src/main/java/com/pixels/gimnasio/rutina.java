package com.pixels.gimnasio;

public class rutina
{
	private String Id,Nombre,Rutina;
	public rutina(){
		
	}
	
	public rutina(String id,String nombre,String rutina){
		this.Id=id;
		this.Nombre=nombre;
		this.Rutina=rutina;
	}
	
	public String getId(){
		return Id;
	}
	public void setId(String id){
		Id=id;
	}
	
	public String getNombre(){
		return Nombre;
	}
	public void setNombre(String nombre){
		Nombre=nombre;
	}
	
	public String getRutina(){
		return Rutina;
	}
	public void setRutina(String rutina){
		Rutina=rutina;
	}
	
	
}
