package com.alf.android.semanasantabilbao.data.entities;

import java.io.Serializable;

/**
 * Created by Alberto Laría Fernández on 03/11/2016.
 */

public class Paso implements Serializable {

    private String id_cofradia, nombrePaso, imagenPaso, textoPaso;

    public String getId_cofradia() {
        return id_cofradia;
    }

    public void setId_cofradia(String id_cofradia) {
        this.id_cofradia = id_cofradia;
    }

    public String getNombrePaso() {
        return nombrePaso;
    }

    public void setNombrePaso(String nombrePaso) {
        this.nombrePaso = nombrePaso;
    }

    public String getImagenPaso() {
        return imagenPaso;
    }

    public void getImagenPaso(String imagenPaso) {
        this.imagenPaso = imagenPaso;
    }

    public String getTextoPaso() {
        return textoPaso;
    }

    public void getTextoPaso(String textoPaso) {
        this.textoPaso = textoPaso;
    }


    public Paso() {
    }
}
