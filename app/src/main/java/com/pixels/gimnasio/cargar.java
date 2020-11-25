package com.pixels.gimnasio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;import android.content.DialogInterface;import android.content.Intent;import android.os.Bundle;import android.support.v7.app.AlertDialog;import android.widget.Toast; import com.android.volley.AuthFailureError;import com.android.volley.Request;import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.StringRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.io.InputStream;import java.util.ArrayList;import java.util.HashMap;import java.util.List;import java.util.Map;

public class cargar extends AppCompatActivity {
	
	String user,cont;
	int p;
	
	public List<usurmm> lista=new ArrayList<>();
	public List<usurmm> lista2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargar);
		Bundle extra = getIntent().getExtras();		
		user=extra.getString("Usuario");		
		cont=extra.getString("Contraseña");
		ip c= new ip();
		final String ipt=c.ip();

		String Url="http://"+ipt+"/usuario.php";
		JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

				@Override
				public void onResponse(JSONArray response) {
					JSONObject jo = null;
					//Toast.makeText(getApplicationContext(), "entro2",Toast.LENGTH_LONG).show();
					for (int i = 0; i < response.length(); i++) {
						try {
							
							jo = response.getJSONObject(i);
							lista.add(new usurmm(jo.getString("username"), jo.getString("password"),jo.getString("cod_pers")));
							
							
						} catch (JSONException e) {
							Toast.makeText(getApplicationContext(), "error siclo bd", Toast.LENGTH_LONG).show();

						}
					}
					int confu=0;
					
					
					for(int i=0;i<lista.size();i++){
						String usurrr=lista.get(i).getUsuario();
						if(usurrr.equals(user)){
							confu=1;
							p=i;
						}
					}
					
					if(confu==1){


						String conr=lista.get(p).getContraseña();
						if(conr.equals(cont)){
							basedeinicio n=new basedeinicio(getApplicationContext());
							n.inic("1",lista.get(p).getId(),user,cont,"1");
							Intent intent =new Intent(cargar.this,usuario.class);
							intent.putExtra("Usuario",user);		
							intent.putExtra("Contraseña",cont);
							intent.putExtra("Codigo",lista.get(p).getId());
							startActivity(intent);
							MainActivity.deci=1;
							finish();
							
						}else{
							Toast.makeText(getApplicationContext(), "contraseña incorrecta",Toast.LENGTH_LONG).show();
							Intent intent =new Intent(cargar.this,MainActivity.class);
							startActivity(intent);
							finish();
						}
					}else{
						//no es usuario
						String Url="http://"+ipt+"/admin.php";
						JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

								@Override
								public void onResponse(JSONArray response) {
									JSONObject jo = null;
									//Toast.makeText(getApplicationContext(), "entro2",Toast.LENGTH_LONG).show();
									for (int i = 0; i < response.length(); i++) {
										try {

											jo = response.getJSONObject(i);
											lista2.add(new usurmm(jo.getString("username"), jo.getString("password"),jo.getString("cod_pers")));


										} catch (JSONException e) {
											Toast.makeText(getApplicationContext(), "error siclo bd", Toast.LENGTH_LONG).show();

										}
									}
									
									
									
									int confo=0;


									for(int i=0;i<lista2.size();i++){
										String usurrr=lista2.get(i).getUsuario();
										if(usurrr.equals(user)){
											confo=1;
											p=i;
										}
									}

									if(confo==1){


										String conr=lista2.get(p).getContraseña();
										
										if(conr.equals(cont)){
											basedeinicio n=new basedeinicio(getApplicationContext());
											n.inic("1",lista.get(p).getId(),user,cont,"0");
											Intent intent =new Intent(cargar.this,administrador.class);
											intent.putExtra("Usuario",user);		
											intent.putExtra("Contraseña",cont);
											intent.putExtra("Codigo",lista.get(p).getId());
											startActivity(intent);
											MainActivity.deci=1;
											finish();
											
										}else{
											Toast.makeText(getApplicationContext(), "contraseña incorrecta",Toast.LENGTH_LONG).show();
											Intent intent =new Intent(cargar.this,MainActivity.class);
											startActivity(intent);
											finish();
										}
									}else{
										Toast.makeText(getApplicationContext(), "Usuario Incorrecto",Toast.LENGTH_LONG).show();
										Intent intent =new Intent(cargar.this,MainActivity.class);
										startActivity(intent);
										finish();
										
									}
									
									
									
									
								}
							}, new Response.ErrorListener() {
								@Override
								public void onErrorResponse(VolleyError error) {
									Toast.makeText(getApplicationContext(), "error sin Internet",Toast.LENGTH_LONG).show();
									Intent intent =new Intent(cargar.this,MainActivity.class);
									startActivity(intent);
									finish();

								}
							});
						RequestQueue requestQueue;
						requestQueue= Volley.newRequestQueue(getApplicationContext());
						requestQueue.add(jsonArrayRequest);
					}
					
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					Toast.makeText(getApplicationContext(), "error sin Internet",Toast.LENGTH_LONG).show();
					Intent intent =new Intent(cargar.this,MainActivity.class);
					startActivity(intent);
					finish();

				}
			});
		RequestQueue requestQueue;
		requestQueue= Volley.newRequestQueue(this);
		requestQueue.add(jsonArrayRequest);
		
		
		
		
    }
	
}
