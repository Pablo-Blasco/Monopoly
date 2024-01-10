package com.pablo.blasco.monopoly.models;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Jugador {

    private String nombre ;
    private int numJuga ;
    private ArrayList callesProp;
    private boolean jugando ;
    private boolean esMiTurno ;
    private double dinero;
    private int posicion ;
    private int carcel;

    public Jugador() {}
    public Jugador(String nombre, int num, ArrayList callesProp, boolean jugando, boolean esMiTurno) {
        this.nombre = nombre;
        this.numJuga = num;
        this.callesProp = callesProp;
        this.jugando = jugando;
        this.esMiTurno = esMiTurno;
        this.posicion=0;
        this.carcel=0;
    }
}
