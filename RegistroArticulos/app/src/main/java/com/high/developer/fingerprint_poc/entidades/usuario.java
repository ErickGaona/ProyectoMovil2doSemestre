package com.high.developer.fingerprint_poc.entidades;

public class usuario {
    private Integer codigo;
    private String  nombre;
    private String  descripcion;
    private String  precio;

    public usuario() {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Integer getCodigo() {

        return codigo;
    }

    public void setCodigo(Integer codigo) {

        this.codigo = codigo;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    public String getPrecio() {

        return precio;
    }

    public void setPrecio(String precio) {

        this.precio = precio;
    }
}

