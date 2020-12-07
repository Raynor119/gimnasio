package com.pixels.gimnasio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.Snackbar;
import android.view.ViewGroup;
import android.support.design.widget.FloatingActionButton;
import android.widget.Button;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;import android.os.Bundle;import android.support.design.widget.CollapsingToolbarLayout;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.TextView;import android.content.DialogInterface;import android.content.Intent;import android.support.design.widget.NavigationView;import android.support.v4.view.GravityCompat;import android.support.v4.widget.DrawerLayout;import android.support.v7.app.ActionBarDrawerToggle;import android.support.v7.app.AlertDialog;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.LinearLayoutManager;import android.support.v7.widget.RecyclerView;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuItem;import android.view.View;import android.widget.TextView;import android.widget.Toast; import com.android.volley.RequestQueue;import com.android.volley.Response;import com.android.volley.VolleyError;import com.android.volley.toolbox.JsonArrayRequest;import com.android.volley.toolbox.Volley; import org.json.JSONArray;import org.json.JSONException;import org.json.JSONObject; import java.util.ArrayList;import java.util.List; import android.widget.*;import android.view.View.OnClickListener;import android.transition.Transition;import android.content.Context;import android.view.animation.LayoutAnimationController;import android.view.animation.AnimationUtils;


public class agregarusu extends AppCompatActivity
{
	EditText cod,cedula,nombre,apellido,edad,direccion,telefono,correo,username,password,datM;
	Button boton;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_agregarusu);
		cod=(EditText)findViewById(R.id.cod);
		cedula=(EditText)findViewById(R.id.cc);
		nombre=(EditText)findViewById(R.id.nom);
		apellido=(EditText)findViewById(R.id.apelli);
		edad=(EditText)findViewById(R.id.edad);
		direccion=(EditText)findViewById(R.id.direcc);
		telefono=(EditText)findViewById(R.id.telef);
		correo=(EditText)findViewById(R.id.corre);
		username=(EditText)findViewById(R.id.usua);
		password=(EditText)findViewById(R.id.contra);
		datM=(EditText)findViewById(R.id.datos);
		boton=(Button)findViewById(R.id.b1);
		
		boton.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(cod.getText().toString().equals("") || cedula.getText().toString().equals("") || nombre.getText().toString().equals("") || apellido.getText().toString().equals("") || edad.getText().toString().equals("") || direccion.getText().toString().equals("") || telefono.getText().toString().equals("") || correo.getText().toString().equals("") || username.getText().toString().equals("") || password.getText().toString().equals("") || datM.getText().toString().equals("") ){
						Toast.makeText(getApplicationContext(), "LLene todos los Campos",Toast.LENGTH_LONG).show();
					}else{
					ip ii=new ip();	
					String ip=ii.ip();		
					String Url="http://"+ip+"/inserusu.php?codigo="+cod.getText()+"&cedula="+cedula.getText()+"&nombre="+nombre.getText()+"&apellido="+apellido.getText()+"&edad="+edad.getText()+"&direccion="+direccion.getText()+"&telefono="+telefono.getText().toString()+"&correo="+correo.getText().toString()+"&usaurio="+username.getText()+"&contrasena="+password.getText()+"&datom="+datM.getText();
					JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Url, new Response.Listener<JSONArray>() {

							@Override
							public void onResponse(JSONArray response) {

							}



						}, new Response.ErrorListener() {
							@Override
							public void onErrorResponse(VolleyError error) {
								new android.os.Handler().postDelayed(new Runnable() {


										@Override
										public void run() {
											//Toast.makeText(getApplicationContext(), "Error de Conexion Verifique su conexion a Internet",Toast.LENGTH_LONG).show();

										}},1);
							}
						});
					RequestQueue requestQueue;
					requestQueue= Volley.newRequestQueue(getApplicationContext());
					requestQueue.add(jsonArrayRequest);
					Toast.makeText(getApplicationContext(), "se Agrego el Usuario",Toast.LENGTH_LONG).show();
					
					finish();
					}
				}
				
			
		});
		}
}
