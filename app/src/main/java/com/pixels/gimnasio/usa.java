package com.pixels.gimnasio;

public class usa {
    private String Id,Codigo,Usuario,Contraseña,Tipo;

    public usa(){

    }
    public usa(String id,String codigo,String usuario,String contraseña,String tipo){
        this.Id=id;
		this.Codigo=codigo;
        this.Usuario=usuario;
        this.Contraseña=contraseña;
        this.Tipo=tipo;
    }
    public String getId(){
        return Id;
    }
    public void setId(String id){
        Id=id;
    }
	public String getCodigo(){
        return Codigo;
    }
    public void setNombre(String codigo){
        Codigo=codigo;
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
    public String getTipo(){
        return Tipo;
    }
    public void setTipo(String tipo){
        Tipo=tipo;
    }
}

