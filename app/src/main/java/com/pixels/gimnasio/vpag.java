package com.pixels.gimnasio;

public class vpag
{
	String Id,IdU,Nombred,Preciod,Nombre,Dias,Precio,Monto,FechaI,Estado,FechaF,Cedula;
	
	public vpag(){
		
	}
	public vpag(String id,String idu,String nombred,String preciod,String nombre,String dias,String precio,String monto,String fechai,String estado,String fechaf,String cedula){
		this.Id=id;
		this.IdU=idu;
		this.Nombred=nombred;
		this.Preciod=preciod;
		this.Nombre=nombre;
		this.Dias=dias;
		this.Precio=precio;
		this.Monto=monto;
		this.FechaI=fechai;
		this.Estado=estado;
		this.FechaF=fechaf;
		this.Cedula=cedula;
	}
	public String getId(){
		return Id;
	}
	public String getIdU(){
		return IdU;
	}
	public String getNombred(){
		return Nombred;
	}
	public String getPreciod(){
		return Preciod;
	}
	public String getNombre(){
		return Nombre;
	}
	public String getDias(){
		return Dias;
	}
	public String getPrecio(){
		return Precio;
	}
	public String getMonto(){
		return Monto;
	}
	public String getFechaI(){
		return FechaI;
	}
	public String getEstado(){
		return Estado;
	}
	public String getFechaF(){
		return FechaF;
	}
	public String getCedula(){
		return Cedula;
	}
}
