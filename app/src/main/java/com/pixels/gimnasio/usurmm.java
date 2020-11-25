package com.pixels.gimnasio;

public class usurmm
{
	private String Usuario,Contraseña,Id;

	public usurmm(){

	}
	public usurmm(String usuario,String contraseña,String id){

		this.Usuario=usuario;
		this.Contraseña=contraseña;
		this.Id=id;
		
	}
	public String getUsuario(){
		return Usuario;
	}
	public void setUsuario(String usuario){
		Usuario=usuario;
	}
	public String getContraseña(){
		return Contraseña;
	}
	public void setContraseña(String contraseña){
		Contraseña=contraseña;
	}
	public String getId(){
		return Id;
	}
	public void setId(String id){
		Id=id;
	}
}
