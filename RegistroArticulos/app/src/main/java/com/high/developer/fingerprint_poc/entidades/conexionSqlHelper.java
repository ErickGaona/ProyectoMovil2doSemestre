package com.high.developer.fingerprint_poc.entidades;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import com.high.developer.fingerprint_poc.entidades.utilidades.utilidades;

public class conexionSqlHelper extends SQLiteOpenHelper {
        // Conexion de la Base de Datos
    public conexionSqlHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(utilidades.CREAR_TABLA_USUARIO);
    }
    // Verifica si existe una version antigua de nuestra base de datos
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
