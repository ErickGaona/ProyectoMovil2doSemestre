package com.high.developer.fingerprint_poc.principal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.high.developer.fingerprint_poc.R;
import com.high.developer.fingerprint_poc.entidades.conexionSqlHelper;
import com.high.developer.fingerprint_poc.entidades.usuario;
import com.high.developer.fingerprint_poc.entidades.utilidades.utilidades;

import java.util.ArrayList;

public class mostrarTodos extends AppCompatActivity {

    Spinner comboArticulos;
    TextView txtCodigo, txtNombre, txtDescripcion, txtPrecio;
    //Representar la informacion que voya presentar en el combo
    ArrayList<String> listaArticulos;
    //Referencias de la tb usuario
    ArrayList<usuario> ArticuloList;
    conexionSqlHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_todos);

        conn = new conexionSqlHelper(getApplicationContext(), "bd_usuarios", null, 1);

        comboArticulos = (Spinner) findViewById(R.id.idSpiner);
        txtCodigo = (TextView) findViewById(R.id.id_codigoC);
        txtNombre = (TextView) findViewById(R.id.id_NombreC);
        txtDescripcion = (TextView) findViewById(R.id.id_DescripcionC);
        txtPrecio = (TextView) findViewById(R.id.id_PrecioC);

        ConsultarPersona();
        //contruir array adpater y le presente la lista de personas
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listaArticulos);
        comboArticulos.setAdapter(adapter);
        //Este es para que cuando me de click me posicione
        comboArticulos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            //metodo para dar posicion
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    txtCodigo.setText(ArticuloList.get(position - 1).getCodigo().toString());
                    txtNombre.setText(ArticuloList.get(position - 1).getNombre());
                    txtDescripcion.setText(ArticuloList.get(position - 1).getDescripcion());
                    txtPrecio.setText(ArticuloList.get(position - 1).getCodigo().toString());

                } else {
                    txtCodigo.setText("");
                    txtNombre.setText("");
                    txtDescripcion.setText("");
                    txtPrecio.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    //Metodo para llamar a la base de datos y obtener la lista de personas
    public void ConsultarPersona() {
        SQLiteDatabase db = conn.getReadableDatabase();

        usuario articulo = null;
        ArticuloList = new ArrayList<usuario>();
        Cursor cursor = db.rawQuery("SELECT * FROM " + utilidades.TABLA_USUARIO, null);
        // Recorrer los registros de la BDD , llena y asignar en el obje personas la infm que llege a la BDD
        while (cursor.moveToNext()) {
            articulo = new usuario();
            articulo.setCodigo(cursor.getInt(0));
            articulo.setNombre(cursor.getString(1));
            articulo.setDescripcion(cursor.getString(2));
            articulo.setPrecio(cursor.getString(3));

            ArticuloList.add(articulo);

        }
        obtenerLista();
    }
        // aqui construimos lista que se nos va a presentar en nuestro spiner
    public void obtenerLista() {
        listaArticulos = new ArrayList<String>();
        listaArticulos.add("Seleccione");
        //Recorrer a la lista de objetos
        for (int i = 0; i < ArticuloList.size(); i++) {
            listaArticulos.add(ArticuloList.get(i).getCodigo() + " -- " + ArticuloList.get(i).getNombre());
        }
    }
}