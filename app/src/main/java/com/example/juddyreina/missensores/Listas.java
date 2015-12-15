package com.example.juddyreina.missensores;

/**
 * Created by sebas on 12/10/2015.
 */
public class Listas {
    private int idImage;
    private String Nombre;


    public Listas(int idImage, String nombre) {
        this.idImage = idImage;
        Nombre = nombre;
    }

    public int getIdImage() {
        return idImage;
    }
    public String getNombre() {
        return Nombre;
    }
}
