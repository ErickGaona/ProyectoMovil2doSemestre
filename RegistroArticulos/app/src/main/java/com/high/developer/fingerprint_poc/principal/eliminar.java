package com.high.developer.fingerprint_poc.principal;

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

public class eliminar extends AppCompatActivity {
    EditText codigo;
    conexionSqlHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);
        codigo=(EditText)findViewById(R.id.id_Eliminarr);
        conn = new conexionSqlHelper(getApplicationContext(), "bd_usuarios", null, 1);
    }
    public void eliminar (View w){
        SQLiteDatabase db=conn.getReadableDatabase();

        String [] parametros = {codigo.getText().toString()};
        //cconsultar BDD
        db.delete(utilidades.TABLA_USUARIO, utilidades.CAMPO_CODIGO+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino el usuario",Toast.LENGTH_LONG).show();
        codigo.setText("");
        db.close();
    }
    public void atras (View f){
        Intent t = new Intent(this, MainActivity5.class);
        startActivity(t);
    }
}