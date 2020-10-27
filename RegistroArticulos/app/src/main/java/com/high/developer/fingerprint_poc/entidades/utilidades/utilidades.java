package com.high.developer.fingerprint_poc.entidades.utilidades;

public class utilidades {

    //CONSTANTES DE LOS CAMPOS DE LA TABLA USUARIO
    public static final String TABLA_USUARIO="usuario";
    public static final String CAMPO_CODIGO="codigo";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_DESCRIPCION="descripcion";
    public static final String CAMPO_PRECIO="precio";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIO+" ("
            +CAMPO_CODIGO+" INTEGER, "
            +CAMPO_NOMBRE+" TEXT, "
            +CAMPO_DESCRIPCION+" TEXT, "
            +CAMPO_PRECIO+" INTEGER)";

}
