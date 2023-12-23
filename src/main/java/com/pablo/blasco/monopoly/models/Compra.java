package com.pablo.blasco.monopoly.models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class Compra {
    Jugador jugador = null;
    Calle calle = null;
    int posicion=0;
    
    public Compra(Jugador jugador, Calle calle, int pos) {
        this.jugador=jugador;
        this.calle = calle;
        posicion=pos;
    }

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

	public Calle getCalle() {
		return calle;
	}

	public void setCalle(Calle calle) {
		this.calle = calle;
	}
    
}
