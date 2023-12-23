package com.pablo.blasco.monopoly.models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Calle {
    String nombreCalle="";

    @Override
    public String toString() {
        return "CallesProp{" + "nombreCalle=" + nombreCalle + ", posicion=" + posicion + ", coste=" + coste + ", estaCompr=" + estaCompr + '}';
    }
    int posicion = 0;
    double coste = 0.0;
    boolean estaCompr = false;
    double pagarAlCaer = 0.0;

    public Jugador getMiDuenyo() {
        return miDuenyo;
    }

    public void setMiDuenyo(Jugador miDuenyo) {
        this.miDuenyo = miDuenyo;
    }
    Jugador miDuenyo = null;
    
    public String getNombreCalle() {
        return nombreCalle;
    }

    public void setNombreCalle(String nombreCalle) {
        this.nombreCalle = nombreCalle;
    }

    public double getPagarAlCaer() {
        return pagarAlCaer;
    }

    public void setPagarAlCaer(double pagarAlCaer) {
        this.pagarAlCaer = pagarAlCaer;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public double getCoste() {
        return coste;
    }

    public void setCoste(double coste) {
        this.coste = coste;
    }

    public boolean isEstaCompr() {
        return estaCompr;
    }

    //public void setEstaCompr(boolean estaCompr) {
    //    this.estaCompr = estaCompr;
    //}
    public Calle(String nombre, int pos, double coste,boolean compr)
    {
        this.nombreCalle = nombre;
        this.posicion = pos;
        this.coste = coste;
        this.estaCompr = compr;
    }

    public Calle() {
    }

	public void setEstaCompr(boolean estaCompr2) {
		this.estaCompr=estaCompr2;
		
	}
}
