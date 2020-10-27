package com.high.developer.fingerprint_poc.principal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.high.developer.fingerprint_poc.MainActivity5;
import com.high.developer.fingerprint_poc.R;
import com.high.developer.fingerprint_poc.entidades.conexionSqlHelper;
import com.high.developer.fingerprint_poc.entidades.utilidades.utilidades;

public class editar extends AppCompatActivity {

    EditText codigo, nombre, descripcion, precio;
    conexionSqlHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);
        // Conexion BDD
        conn = new conexionSqlHelper(getApplicationContext(),"bd_usuarios",null,1);

        codigo=(EditText)findViewById(R.id.id_codigoEditar);
        nombre=(EditText)findViewById(R.id.id_nombreEditar);
        descripcion=(EditText)findViewById(R.id.id_descripcionEditar);
        precio=(EditText)findViewById(R.id.idPrecioEditar);
    }
    public void actualizar (View w){
        SQLiteDatabase db=conn.getReadableDatabase();
        String [] parametros = {codigo.getText().toString()};

        ContentValues values = new ContentValues();
        values.put(utilidades.CAMPO_NOMBRE, nombre.getText().toString());
        values.put(utilidades.CAMPO_DESCRIPCION, descripcion.getText().toString());
        values.put(utilidades.CAMPO_PRECIO, precio.getText().toString());
        // consukta de actualizar
        db.update(utilidades.TABLA_USUARIO,values,utilidades.CAMPO_CODIGO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Ya se actualizo",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void atras(View v){
        Intent t = new Intent(this, MainActivity5.class);
        startActivity(t);
    }
}