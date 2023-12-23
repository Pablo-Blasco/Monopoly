package com.pablo.blasco.monopoly.models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

/**
 *
 * @author danie
 */
public class Jugador {
    String nombre = "";
    int numJuga = 0;   
    ArrayList callesProp = new ArrayList();
    boolean jugando = false;
    //CallesProp callesProp = null;
    boolean esMiTurno = false;
    double dinero = 0.0f;
    public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	int posicion = 0;
    
  public double getDinero() {
		return dinero;
	}
	public void setDinero(double dinero) {
		this.dinero = dinero;
	}
	
public Jugador()
  {
	  
  }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumJuga() {
        return numJuga;
    }

    public void setNumJuga(int numJuga) {
        this.numJuga = numJuga;
    }

    public ArrayList getCallesProp() {
        return callesProp;
    }

    public boolean isEsMiTurno() {
        return esMiTurno;
    }

    public void setEsMiTurno(boolean esMiTurno) {
        this.esMiTurno = esMiTurno;
    }

    public void setCallesProp(ArrayList callesProp) {
        this.callesProp = callesProp;
    }

    public Jugador(String nombre, int num,ArrayList callesProp, boolean jugando, boolean esMiTurno) {
        this.nombre = nombre;
        this.numJuga=num;
        this.callesProp = callesProp;
        this.jugando = jugando;
        this.esMiTurno = esMiTurno;
    }

    public boolean isJugando() {
        return jugando;
    }

    public void setJugando(boolean jugando) {
        this.jugando = jugando;
    }
         

}
