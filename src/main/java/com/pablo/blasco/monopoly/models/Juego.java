package com.pablo.blasco.monopoly.models;


import com.pablo.blasco.monopoly.service.GestorCompraPaga;
import lombok.Getter;
import org.apache.juli.logging.Log;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@Getter
public class Juego {
    final int NCASILLAS = 39;
    final int CARCEL = 10;

    ArrayList<Calle> tablero = null;
    ArrayList<Jugador> jugadores = null;
    ArrayList<Compra> compraArray = null;
	Banca banca =null;
    String mensaje="";


    public Juego(ArrayList jugadores, ArrayList tablero, boolean listadosB) {
        this.jugadores = jugadores;
        this.tablero = tablero;
        compraArray = new ArrayList();

        listadosB = listadosB;

    }

    public void jugar() {
         this.banca = new Banca(1000.f, "Inicio", false);
    }

    public Jugador jugar(Jugador jugador,int dados) {
        int pos = jugador.getPosicion();
        pos = pos + dados;
        if (pos >= NCASILLAS) {
            this.mensaje="Pasas por el origen.Recibes 20e ";
            System.out.println("Pasas por el origen.Recibes 20e ");
            float dineroAhora = (float) jugador.getDinero();
            jugador.setDinero(dineroAhora + 20.0f);
            ///// HACER LOS CALCULOS PARA EL PASO
            pos = pos - NCASILLAS;
        }
        jugador.setPosicion(pos);
        System.out.println("Estas en la casilla " + pos);
        return jugador;
    }


}


