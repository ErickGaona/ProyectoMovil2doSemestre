package com.high.developer.fingerprint_poc;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.high.developer.fingerprint_poc.entidades.conexionSqlHelper;
import com.high.developer.fingerprint_poc.principal.buscar6;
import com.high.developer.fingerprint_poc.principal.editar;
import com.high.developer.fingerprint_poc.principal.eliminar;
import com.high.developer.fingerprint_poc.principal.mostrarTodos;
import com.high.developer.fingerprint_poc.entidades.utilidades.utilidades;

public class MainActivity5 extends AppCompatActivity {

    EditText Tcodigo, Tnombre, Tdescripcion, Tprecio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Tcodigo = (EditText)findViewById(R.id.idCodigoAgregar);
        Tnombre = (EditText)findViewById(R.id.idNombreAgregar);
        Tdescripcion = (EditText)findViewById(R.id.idDesceipcionAgregar);
        Tprecio = (EditText)findViewById(R.id.idPrecioAgregar);

    }
    public void registrar (View w){


        conexionSqlHelper conn = new conexionSqlHelper(this,"bd_usuarios",null,1);
        //modo lectura y escritura
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(utilidades.CAMPO_CODIGO,Tcodigo.getText().toString());
        values.put(utilidades.CAMPO_NOMBRE,Tnombre.getText().toString());
        values.put(utilidades.CAMPO_DESCRIPCION,Tdescripcion.getText().toString());
        values.put(utilidades.CAMPO_PRECIO,Tprecio.getText().toString());

        String insert="INSERT INTO "+utilidades.TABLA_USUARIO+" ( "
                +utilidades.CAMPO_CODIGO+","
                +utilidades.CAMPO_NOMBRE+","
                +utilidades.CAMPO_DESCRIPCION+","
                +utilidades.CAMPO_PRECIO+")"+

                "VALUES ("+Tcodigo.getText().toString()+", '"
                +Tnombre.getText().toString()+"','"
                +Tdescripcion.getText().toString()+"','"
                +Tprecio.getText().toString()+"')";
                db.execSQL(insert);
        Toast.makeText(this, "Se guardo Correctamente", Toast.LENGTH_SHORT).show();
        db.close();

        Tcodigo.setText("");
        Tnombre.setText("");
        Tdescripcion.setText("");
        Tprecio.setText("");

        //Cambio de Actividades

    }

    public void editar (View v){
        Intent i = new Intent(this, editar.class);
        startActivity(i);
    }
    public void buscar (View v){
        Intent i = new Intent(this, buscar6.class);
        startActivity(i);
    }
    public void eliminar(View v){
        Intent i = new Intent(this, eliminar.class);
        startActivity(i);
    }
    public void mostrarTodos(View w){
        Intent i = new Intent(this, mostrarTodos.class);
        startActivity(i);
    }
}
