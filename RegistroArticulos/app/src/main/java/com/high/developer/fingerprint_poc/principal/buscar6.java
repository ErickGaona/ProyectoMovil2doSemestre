package com.high.developer.fingerprint_poc.principal;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.high.developer.fingerprint_poc.MainActivity5;
import com.high.developer.fingerprint_poc.R;
import com.high.developer.fingerprint_poc.entidades.conexionSqlHelper;
import com.high.developer.fingerprint_poc.entidades.utilidades.utilidades;

public class buscar6 extends AppCompatActivity {
    EditText B_codigo;
    TextView B_nombre, B_descripcion, B_precio;
    conexionSqlHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        //acesso BD
        conn = new conexionSqlHelper(getApplicationContext(), "bd_usuarios", null, 1);

        B_codigo = (EditText) findViewById(R.id.idCodigoBuscar);
        B_nombre = (TextView) findViewById(R.id.idNombreBuscar);
        B_descripcion = (TextView) findViewById(R.id.id_DescripcionBuscar);
        B_precio = (TextView) findViewById(R.id.id_PrecioBuscar);
    }

    public void buscar(View v) {

        SQLiteDatabase db = conn.getReadableDatabase();
        //parametros de consulta
        String[] parametros = {B_codigo.getText().toString()};
        try {
            //campos que queremos que se visualizen con el codigo
            Cursor cursor = db.rawQuery(" SELECT " + utilidades.CAMPO_NOMBRE + "," + utilidades.CAMPO_DESCRIPCION + "," + utilidades.CAMPO_PRECIO + " FROM " + utilidades.TABLA_USUARIO + " WHERE " + utilidades.CAMPO_CODIGO + "=?", parametros);
            cursor.moveToFirst();
            //posiciones
            B_nombre.setText(cursor.getString(0));
            B_descripcion.setText(cursor.getString(1));
            B_precio.setText(cursor.getString(2));
            cursor.close();

        } catch (Exception e) {
            Toast.makeText(this, "No existe ese codigo", Toast.LENGTH_SHORT).show();
            B_codigo.setText(" ");
            B_nombre.setText(" ");
            B_descripcion.setText(" ");
            B_precio.setText(" ");
        }
    }
    public void atras(View v){
        Intent i = new Intent(this, MainActivity5.class);
        startActivity(i);
    }
}