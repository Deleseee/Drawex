package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Registro extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        et1= (EditText) findViewById(R.id.usuario);
        et2= (EditText) findViewById(R.id.contra);
    }
    public void registrar(View view){
        try {
            InputStreamReader ar = new InputStreamReader(openFileInput("registro.txt"));
            BufferedReader br = new BufferedReader(ar);
            String usuario="";
            boolean entrar = true;
            boolean existe=false;
            while(usuario!=null&&entrar){
                usuario = br.readLine();
                if (et1.getText().toString().equals(usuario)){
                    existe=true;
                    entrar=false;
                }
                br.readLine();
            }
            if (et1.getText().toString().equals("")&&et2.getText().toString().equals("")){
                Toast t = Toast.makeText(this, "El usuario o contraseña no puede ser nulo", Toast.LENGTH_SHORT);
                t.show();
            }else if(existe){
                Toast t = Toast.makeText(this, "El usuario ya existe", Toast.LENGTH_SHORT);
                t.show();
            }else{
                File a = new File(getApplicationContext().getFilesDir(),"registro.txt");
                OutputStreamWriter archivo;
                if (!a.exists()){
                    archivo = new OutputStreamWriter(openFileOutput("registro.txt", Registro.MODE_PRIVATE));
                }
                else {
                    archivo = new OutputStreamWriter(openFileOutput("registro.txt", Registro.MODE_APPEND));
                }
                archivo.write(et1.getText().toString()+"\n");
                archivo.write(et2.getText().toString()+"\n");
                archivo.flush();
                archivo.close();
                Toast t = Toast.makeText(this, "El registro ha sido correcto", Toast.LENGTH_SHORT);
                t.show();
                finish();
            }
        } catch (IOException e){
        }
    }
}