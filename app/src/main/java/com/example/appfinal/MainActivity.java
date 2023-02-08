package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    private EditText et1;
    private EditText et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1= (EditText) findViewById(R.id.usuario1);
        et2= (EditText) findViewById(R.id.contra1);
    }
    public void registro(View view) {
       Intent intent = new Intent(this,Registro.class);
       startActivity(intent);
    }
    public void inicio(View view) {
        String nombre = et1.getText().toString();
        String contra = et2.getText().toString();
        try {
            InputStreamReader archivo = new InputStreamReader(openFileInput("registro.txt"));
            BufferedReader br = new BufferedReader(archivo);
            String usuario="";
            String contr="";
            while(usuario!=null){
                usuario = br.readLine();
                contr = br.readLine();
                if (nombre.equals(usuario)&&contr.equals(contra)){
                    Toast t = Toast.makeText(this, "Bienvenido!!", Toast.LENGTH_SHORT);
                    t.show();
                    br.close();
                    archivo.close();
                    Intent intent = new Intent(this,Dibujar.class);
                    startActivity(intent);
                }
            }
            Toast t = Toast.makeText(this, "Usuario o contraseña incorrecto", Toast.LENGTH_SHORT);
            t.show();
            br.close();
            archivo.close();
        } catch (IOException e) {
        }
    }
}