package com.ecopredict.dto;

public class UsuarioDTO {
    private String nombre;
    private String correo;
    private String contrasena;
    private int numHabitantes;

    // Constructor vacío obligatorio para que Spring procese el JSON
    public UsuarioDTO() {
    }

    
    public UsuarioDTO(String nombre, String correo, String contrasena, int numHabitantes) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.numHabitantes = numHabitantes;
    }

    // Métodos Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public int getNumHabitantes() {
        return numHabitantes;
    }

    public void setNumHabitantes(int numHabitantes) {
        this.numHabitantes = numHabitantes;
    }
}