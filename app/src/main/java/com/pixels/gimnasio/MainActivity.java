package com.pixels.gimnasio;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
	public static int deci=0;
    EditText email,conta;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText) findViewById(R.id.editTextEmail);
        conta=(EditText) findViewById(R.id.editTextPassword);
    }
    public void onclic(View view){
		
		String usr=String.valueOf(email.getText().toString());
		String cont=String.valueOf(conta.getText().toString());						
		if(usr.equals("")||cont.equals("")){			
			Toast.makeText(getApplicationContext(), "Digite el Usuario o Contraseña",Toast.LENGTH_LONG).show();	
		}		
		else{
		
        Intent intent =new Intent(MainActivity.this,cargar.class);
		intent.putExtra("Usuario",usr);		
		intent.putExtra("Contraseña",cont);
		startActivity(intent);
		finish();
		
		}
    }
}
