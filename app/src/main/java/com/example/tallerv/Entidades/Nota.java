package com.example.tallerv.Entidades;

public class Nota {

    private int id;
    private String tituloNotaTxt, localizacionNotaTxt, descripcionNotaTxt, fechaNotaTxt;

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getTituloNotaTxt() {
        return tituloNotaTxt;
    }

    public void setTituloNotaTxt(String tituloNotaTxt) { this.tituloNotaTxt = tituloNotaTxt; }

    public String getLocalizacionNotaTxt() {
        return localizacionNotaTxt;
    }

    public void setLocalizacionNotaTxt(String localizacionNotaTxt) { this.localizacionNotaTxt = localizacionNotaTxt; }

    public String getDescripcionNotaTxt() {
        return descripcionNotaTxt;
    }

    public void setDescripcionNotaTxt(String descripcionNotaTxt) { this.descripcionNotaTxt = descripcionNotaTxt; }

    public String getFechaNotaTxt() {
        return fechaNotaTxt;
    }

    public void setFechaNotaTxt(String fechaNotaTxt) {
        this.fechaNotaTxt = fechaNotaTxt;
    }
}