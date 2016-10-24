package com.alf.android.semanasantabilbao.data.entities;

import java.io.Serializable;

/**
 * Created by alaria on 25/04/2016.
 */
public class Cofradia implements Serializable {

    private String id_cofradia, nombreCofradia, sede, direccion, telefono, web, email, imagenEscudo, imagenDetalle;
    private String textoDetalle, hermanoAbad, vestimenta, textoIntroPasos;
    private int fundacion, numeroPasos, numeroProcesiones;


    public String getId_cofradia() {
        return id_cofradia;
    }

    public void setId_cofradia(String id_cofradia) {
        this.id_cofradia = id_cofradia;
    }

    public String getNombreCofradia() {
        return nombreCofradia;
    }

    public void setNombreCofradia(String nombreCofradia) {
        this.nombreCofradia = nombreCofradia;
    }

    public int getFundacion() {
        return fundacion;
    }

    public void setFundacion(int fundacion) {
        this.fundacion = fundacion;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagenEscudo() {
        return imagenEscudo;
    }

    public void setImagenEscudo(String imagenEscudo) {
        this.imagenEscudo = imagenEscudo;
    }

    public String getImagenDetalle() {
        return imagenDetalle;
    }

    public void setImagenDetalle(String imagenDetalle) {
        this.imagenDetalle = imagenDetalle;
    }

    public String getTextoDetalle() {
        return textoDetalle;
    }

    public void setTextoDetalle(String textoDetalle) {
        this.textoDetalle = textoDetalle;
    }

    public String getHermanoAbad() {
        return hermanoAbad;
    }

    public void setHermanoAbad(String hermanoAbad) {
        this.hermanoAbad = hermanoAbad;
    }

    public String getVestimenta() {
        return vestimenta;
    }

    public void setVestimenta(String vestimenta) {
        this.vestimenta = vestimenta;
    }

    public int getNumeroPasos() {
        return numeroPasos;
    }

    public void setNumeroPasos(int numeroPasos) {
        this.numeroPasos = numeroPasos;
    }

    public String getTextoIntroPasos() {
        return textoIntroPasos;
    }

    public void setTextoIntroPasos(String textoIntroPasos) {
        this.textoIntroPasos = textoIntroPasos;
    }

    public int getNumeroProcesiones() {
        return numeroProcesiones;
    }

    public void setNumeroProcesiones(int numeroProcesiones) {
        this.numeroProcesiones = numeroProcesiones;
    }


    public Cofradia() {
    }

    public Cofradia(String id_cofradia, String nombreCofradia, String imagenEscudo, String imagenDetalle) {
        this.id_cofradia = id_cofradia;
        this.nombreCofradia = nombreCofradia;
        this.imagenEscudo = imagenEscudo;
        this.imagenDetalle = imagenDetalle;
    }
}
