package com.pixels.gimnasio;

public class usu
{
	private String Cod,Cedula,Nombre,Apellido;
	
	public usu(){
		
	}
	
	public usu(String cod,String cedula,String nombre,String apellido){
			this.Cod=cod;
			this.Cedula=cedula;
			this.Nombre=nombre;
			this.Apellido=apellido;
	}
	
	public String getCod(){
		return Cod;
	}
	public void setCod(String cod){
		Cod=cod;
	}
	
	public String getCedula(){
		return Cedula;
	}
	public void setCedula(String cedula){
		Cedula=cedula;
	}
	
	public String getNombre(){
		return Nombre;
	}
	public void setNombre(String nombre){
		Nombre=nombre;
	}
	
	public String getApellido(){
		return Apellido;
	}
	public void setApellido(String apellido){
		Apellido=apellido;
	}
}
