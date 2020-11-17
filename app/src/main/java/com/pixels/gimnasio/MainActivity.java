package com.pixels.gimnasio;

import android.app.*;
import android.content.Intent;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
{
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
        Intent intent =new Intent(MainActivity.this,cargar.class);
        startActivity(intent);
    }
}
