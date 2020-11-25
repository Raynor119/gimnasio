package com.pixels.gimnasio;

import android.support.v7.app.*;
import android.os.*;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;
import android.content.IntentSender;
import java.util.ArrayList;
import java.util.List;

public class inicio extends AppCompatActivity
{
	@Override 
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activiy_inicio);
		new android.os.Handler().postDelayed(new Runnable() { 		
				@Override public void run() { 
					Intent intent; 		
					switch(getFirstTimeRun(this)) { 		
						case 0:
							basedeinicio n=new basedeinicio(getApplicationContext()); 
							n.agregarE("1","nada","nada","nada","nada");
							intent = new Intent(inicio.this,MainActivity.class); 	
							startActivity(intent); 			
							finish();			
							break; 		
						case 1:
							basedeinicio ne=new basedeinicio(getApplicationContext());
							List<usa> usurr=new ArrayList<>();
							usurr=ne.obtusur();
							String usu=usurr.get(0).getUsuario();
							System.out.println("usuario: "+usu);
							if(usu.equals("nada")){
							intent = new Intent(inicio.this, MainActivity.class); 		
							startActivity(intent); 
							finish();	
							}else{
								if(usurr.get(0).getTipo().equals("0")){
									//administrador
									intent =new Intent(inicio.this,administrador.class);
									intent.putExtra("Usuario",usurr.get(0).getUsuario());		
									intent.putExtra("Contraseña",usurr.get(0).getContraseña());
									intent.putExtra("Codigo",usurr.get(0).getCodigo());
									startActivity(intent);
									finish();
								}else{
									//usuario
									intent =new Intent(inicio.this,usuario.class);
									intent.putExtra("Usuario",usurr.get(0).getUsuario());		
									intent.putExtra("Contraseña",usurr.get(0).getContraseña());
									intent.putExtra("Codigo",usurr.get(0).getCodigo());
									startActivity(intent);
									finish();
								}
							}
							break; 		
						case 2:		
							finish();
							break; 				
					} 			
				} 	
			},1000); 
		
		
	}
	
	
	public int getFirstTimeRun(Runnable contexto) { 	
	SharedPreferences sp = getSharedPreferences("MYAPP", 0); 	
	int result, currentVersionCode = 2; 	
	int lastVersionCode = sp.getInt("FIRSTTIMERUN", -1); 	
	if (lastVersionCode == -1) 		
		result = 0; 	
	else 		
		result = (lastVersionCode == currentVersionCode) ? 1 : 2; 
		sp.edit().putInt("FIRSTTIMERUN", currentVersionCode).apply(); 		
	return result; 	
	}
	
}
