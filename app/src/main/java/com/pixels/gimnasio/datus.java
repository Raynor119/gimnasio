package com.pixels.gimnasio;

public class datus
{
	private String Id,Datom,Cedula,Nombre,Apellido,Edad,Direccion,Telefono,Correo,Username,Password;
	public datus(){

	}
	public datus(String id,String datom,String cedula,String nombre,String apellido,String edad,String direccion,String telefono,String correo,String username,String password){
		this.Id=id;
		this.Datom=datom;
		this.Cedula=cedula;
		this.Nombre=nombre;
		this.Apellido=apellido;
		this.Edad=edad;
		this.Direccion=direccion;
		this.Telefono=telefono;
		this.Correo=correo;
		this.Username=username;
		this.Password=password;
	}
	public String getId(){
		return Id;
	}
	public String getDatom(){
		return Datom;
	}
	public String getCedula(){
		return Cedula;
	}
	public String getNombre(){
		return Nombre;
	}
	public String getApellidfo(){
		return Apellido;
	}
	public String getEdad(){
		return Edad;
	}
	public String getDireccion(){
		return Direccion;
	}
	public String getTelefono(){
		return Telefono;
	}
	public String getCorreo(){
		return Correo;
	}
	public String getUsername(){
		return Username;
	}
	public String getPassword(){
		return Password;
	}
}
