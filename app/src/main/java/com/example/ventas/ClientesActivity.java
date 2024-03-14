package com.example.ventas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class ClientesActivity extends AppCompatActivity {

    EditText etidentificacion,etnombre,etdireccion,ettelefono;
    Button btbuscar,btguardar,btanula,btlimpiar,btregresar;

    CheckBox cbactivo;

    //definiendo variables
    String identificacion,nombre,direccion,telefono;

    long respuesta;
    ClsOpenHelper objconexion=new ClsOpenHelper(this,"Concesionario.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientes);

        //Asociar Objetos Java con Objetos XML
        etidentificacion=findViewById(R.id.etidentificacion);
        etnombre=findViewById(R.id.etnombre);
        etdireccion=findViewById(R.id.etdireccion);
        ettelefono=findViewById(R.id.ettelefono);
        btbuscar=findViewById(R.id.btbuscar);
        btguardar=findViewById(R.id.btguardar);
        btanula=findViewById(R.id.btanular);
        btlimpiar=findViewById(R.id.btlimpiar);
        btregresar=findViewById(R.id.btregresar);
        cbactivo=findViewById(R.id.cbactivo);
        etidentificacion.requestFocus();

    }//Fin del Metodo onCreate

    public void Guardar(View view){
        //pasar la informacion de los objetos a las variablñes
        identificacion=etidentificacion.getText().toString();
        nombre=etnombre.getText().toString();
        telefono=ettelefono.getText().toString();
        direccion=etdireccion.getText().toString();

        //validar que no hay variables vacias
        if (!identificacion.isEmpty() && !nombre.isEmpty() && !direccion.isEmpty() && !telefono.isEmpty()){

            //Abri la conexion en modo de lectura
            SQLiteDatabase admin=objconexion.getWritableDatabase();

            //instanciar el contenedor
            ContentValues registro=new ContentValues();
            registro.put("Ident_cliente",identificacion);
            registro.put("Nom_cliente",nombre);
            registro.put("Dir_cliente",direccion);
            registro.put("Tel_cliente",telefono);
            //Guardar el registro en la tabla TBLCliente de la base de datos Concesionario.db
            respuesta= admin.insert("TblCliente","null",registro);
            //validar si lo hizo o no
            if (respuesta > 0){
                Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Error guardando registro", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Todos los datos son requeridos", Toast.LENGTH_SHORT).show();
            etidentificacion.requestFocus();
        }
    }//fin de Guardar se debe poner la libreria de vistas si no no se puede ver

    public void Consultar(View view){
    //llevar la informacion del objeto a la variable
        identificacion=etidentificacion.getText().toString();
        //validar que si digito la identificacion
        if (identificacion.isEmpty()) {
            //Abrir la base de datos en modo lectura
            SQLiteDatabase admin=objconexion.getReadableDatabase();
        }else{
            Toast.makeText(this, "Identificacion es requerida para consultar", Toast.LENGTH_SHORT).show();
        }
    }//fin metodo consultar
    public void Limpiar(View view){
        Limpiar_campos();
    }//fin metodo limpiar

    public void Regresar(View view){
        Intent intmain=new Intent(this, MainActivity.class);
        startActivity(intmain);
    }//fin metodo Regresar


    private void Limpiar_campos(){
        etidentificacion.setText("");
        etnombre.setText("");
        etdireccion.setText("");
        ettelefono.setText("");
        etidentificacion.requestFocus();
    }//Fin metodo Limpiar Campos

}