/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pablo.blasco.monopoly.service;

import com.pablo.blasco.monopoly.models.Banca;
import com.pablo.blasco.monopoly.models.Calle;
import com.pablo.blasco.monopoly.models.Compra;
import com.pablo.blasco.monopoly.models.Jugador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class GestorCompraPaga {

    ArrayList<Banca> bancaArray = null;

    public GestorCompraPaga() {
        bancaArray = new ArrayList();
    }

    public boolean estaComprada(ArrayList tablero, int pos) {
        Calle calle = (Calle) tablero.get(pos - 1);
        if (calle.isEstaCompr()) {
            System.out.println("estacomprada. Continua el juego");
            return true;
        } else {
            System.out.println("no estacomprada. Continua el juego");
            return false;
        }
    }
    public Calle devuelveCalle(ArrayList tablero, int pos) {
        Calle calle = (Calle) tablero.get(pos - 1);
        System.out.println("devuelve calle. Continua el juego");
       return calle;
    }
    public boolean esEspecial(ArrayList tablero, int pos) {
        Calle calle = (Calle) tablero.get(pos - 1);
        if (calle.getTipoCalle().equals("E")) {
            System.out.println("es especial. Continua el juego");
            return true;
        } else {
            System.out.println("No es especial. Continua el juego");
            return false;
        }
    }
    public boolean esImpuesto(ArrayList tablero, int pos) {
        Calle calle = (Calle) tablero.get(pos - 1);
        if (calle.getTipoCalle().equals("I")) {
            System.out.println("es Impuesto. Continua el juego");
            return true;
        } else {
            System.out.println("No es Impuesto. Continua el juego");
            return false;
        }
    }

    public Calle devuelveEspecial(ArrayList tablero, int pos) {
        Calle calle = (Calle) tablero.get(pos - 1);
        return calle;

    }

    public String compra(ArrayList tablero, ArrayList<Compra> compraArray, Jugador jugador, int pos, Banca banca) {
        Calle calle = (Calle) tablero.get(pos - 1);
        double coste = calle.getCoste();
        double pagaSiCae = calle.getPagarAlCaer();

        double suDinero = jugador.getDinero();
        if (coste <= suDinero) {
            jugador.setDinero(suDinero - coste);
            boolean estaCompr2 = true;
            calle.setEstaCompr(estaCompr2);
            Compra compra = new Compra(jugador, calle, pos,"Calle comprada por "+ jugador.getNombre());
            compraArray.add(compra);
            ((Calle) tablero.get(pos-1)).setEstaCompr(true);
            return "Calle comprada por "+ jugador.getNombre();
        } else {

            System.out.println("NO PUEDES COMPRAR POR FALTA DE DINERO");
            return "NO PUEDES COMPRAR POR FALTA DE DINERO";
        }
    }

    public void quienDuenyo(int pos, Jugador jugadorTURNO, ArrayList compraArray) {
        Iterator it = compraArray.iterator();
        String mensaje="";
        boolean exito = false;
        System.out.println("Entroen quien. Continua el juego");
        while ((it.hasNext()) && (!exito)) {
            Compra compra = (Compra) it.next();
            System.out.println("<<<<<<<<<< " + compra.getPosicion() + " <<<<< " + pos);
            System.out.println("casilla " + compra.getPosicion() + "  " + compra.getCalle().getNombreCalle() + "  " + compra.getCalle().getCoste() + "    " + compra.getJugador().getNombre() + " // ");
            if (compra.getJugador().getNombre().equals(jugadorTURNO.getNombre()) && (compra.getPosicion() == pos)) // <------------ no solo basta con chequear el nombre, hay que chequear posicion porque puede tener varias compras
            {
                System.out.println("Es tu propiedad. Continua el juego");
            } else if (compra.getPosicion() == pos) {
                compra.setMensajeCompra("EL dueño es" + compra.getJugador().getNombre() + " por lo que habra un pago de " + jugadorTURNO.getNombre() + " a " + compra.getJugador().getNombre());
                System.out.println("EL dueño es" + compra.getJugador().getNombre() + " por lo que habra un pago de " + jugadorTURNO.getNombre() + " a " + compra.getJugador().getNombre());
                // QUEDA EFECTUAR EL PAGO
                double aPagar = compra.getCalle().getPagarAlCaer();
                efectuarElPago(jugadorTURNO, compra.getJugador(), aPagar);
                exito = true;
            }
        }
    }
    public void pagaImpuesto(ArrayList tablero, Jugador jugador, int pos) {
        Calle calle = (Calle) tablero.get(pos - 1);
        double pagaSiCae = calle.getPagarAlCaer();
        double money = jugador.getDinero();
        if (money < pagaSiCae) {
            jugador.setJugando(false);
            jugador.setDinero(0);
        }else {
            double dineroFinal=money-pagaSiCae;
            jugador.setDinero(dineroFinal);
        }
    }
    private void efectuarElPago(Jugador jugadorTURNO, Jugador jugador, double aPagar) {
        double moneydescontar = jugadorTURNO.getDinero();
        moneydescontar = moneydescontar - aPagar;
        if (moneydescontar < 0) {
            jugadorTURNO.setDinero(0);
            jugadorTURNO.setJugando(false);
        }else{
            jugadorTURNO.setDinero(moneydescontar);
        }
        double moneySumar=jugador.getDinero();
        moneySumar += aPagar;
        jugador.setDinero(moneySumar);

    }

    public void listaCompras(ArrayList compraArray) {
        Iterator it = compraArray.iterator();
        System.out.println("TENEMOS " + compraArray.size() + " COMPRAS");
        System.out.println("_____________COMPRAS_____________");
        while (it.hasNext()) {
            Compra compra = (Compra) it.next();
            System.out.println("CALLE casilla " + compra.getPosicion() + " " + compra.getCalle().getNombreCalle() + "  " + compra.getCalle().getCoste() + " // JUGADOR   " + compra.getJugador().getNombre());
        }
        System.out.println("________________________________");
    }

    private void listaBanca() {
        Iterator it = this.bancaArray.iterator();
        System.out.println("_____________COMPRAS_____________");
        while (it.hasNext()) {
            Banca banca = (Banca) it.next();
            System.out.println(banca.getDinero() + "   " + banca.getCausaHistorico());
        }
        System.out.println("________________________________");
    }

}
