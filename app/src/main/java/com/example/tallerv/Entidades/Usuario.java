package com.example.tallerv.Entidades;

public class Usuario {

    private int id;
    private String usuario;
    private String email;
    private String contrasena;
    private String repcontrasena;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRepcontrasena() {
        return repcontrasena;
    }

    public void setRepcontrasena(String repcontrasena) {
        this.repcontrasena = repcontrasena;
    }
}
