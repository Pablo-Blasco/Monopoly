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

    public void compraOPaga(ArrayList tablero, ArrayList compraArray, Jugador jugador, int pos, Banca banca) {
        Calle calle = (Calle) tablero.get(pos - 1);
        Scanner sca = new Scanner(System.in);
        double coste = calle.getCoste();
        double pagaSiCae = calle.getPagarAlCaer();
        double dineroBanca = 0.0f;
        // si calle NO esta comprada
        String siono;
        if (calle.isEstaCompr() == false) {
            do {
                System.out.println("Calle para la compra ------->>>>> " + calle.getNombreCalle() + "  coste   " + calle.getCoste() + " si alguien cae" + calle.getPagarAlCaer() + " // casilla:  " + pos);

                 siono = sca.nextLine();
                
            }while (siono.length() != 1);
                siono = siono.toUpperCase();
                // La consola recoge el valor
                if (!(calle.getNombreCalle().equals("SUERTE") || calle.getNombreCalle().equals("CAJA DE COMUNIDAD"))) {
                    if (siono.charAt(0) == 'S') {
                        double suDinero = jugador.getDinero();
                        // puede comprarla?
                        if (suDinero > coste) {
                            //comprar
                            jugador.setDinero(suDinero - coste);
                            // A EXTINGUIRSystem.out.println("oooooo COMPRA"+ jugador.getCallesProp().size());
                            boolean estaCompr2 = true; // ARREGLO 
                            calle.setEstaCompr(estaCompr2);
                            // Banca
                            dineroBanca = banca.getDinero();
                            dineroBanca -= coste;
                            if (dineroBanca < 0.0f) {
                                banca.isEnQuiebra();
                                System.out.println("QUIEBRA DE BANCA ooooooooooooooooooo");
                                listaBanca();
                                System.exit(-1);
                            }
                            banca.setDinero(dineroBanca);
                            banca.setCausaHistorico("Compra aprobada " + calle.getNombreCalle() + "  coste   " + calle.getCoste() + jugador.getNombre());
                            Compra compra = new Compra(jugador, calle, pos);
                            compraArray.add(compra);
                        } else {
                            System.out.println("NO PUEDES COMPRAR POR FALTA DE DINERO");
                        }
                    }

                } else {
                    System.out.println("ESTA COMPRADA, LO SIENTO, VAS A TEner que PAGAR");
                    // pueden pasar varias cosas: es de otro o es suya y hay que localizar quien es el dueño
                    quienDuenyo(pos, jugador, compraArray);
                }
            }
        }
   
 

    public void quienDuenyo(int pos, Jugador jugadorTURNO, ArrayList compraArray) {
        Iterator it = compraArray.iterator();
        System.out.println("_____________COMPRAS PARA CHEQUEAR DUENO_____________");
        boolean exito = false;

        while ((it.hasNext()) && (!exito)) {
            Compra compra = (Compra) it.next();
            System.out.println("<<<<<<<<<< " + compra.getPosicion() + " <<<<< " + pos);
            System.out.println("casilla " + compra.getPosicion() + "  " + compra.getCalle().getNombreCalle() + "  " + compra.getCalle().getCoste() + "    " + compra.getJugador().getNombre() + " // ");
            if (compra.getJugador().getNombre().equals(jugadorTURNO.getNombre()) && (compra.getPosicion() == pos)) // <------------ no solo basta con chequear el nombre, hay que chequear posicion porque puede tener varias compras
            {
                System.out.println("Es tu propiedad. Continua el juego");
            } else if (compra.getPosicion() == pos) {
                System.out.println("EL dueño es" + compra.getJugador().getNombre() + " por lo que habra un pago de " + jugadorTURNO.getNombre() + " a " + compra.getJugador().getNombre());
                // QUEDA EFECTUAR EL PAGO
                double aPagar = compra.getCalle().getPagarAlCaer();

                System.out.println("$$$$$$$ " + aPagar);
                efectuarElPago(jugadorTURNO, compra.getJugador(), aPagar);
                exito = true;
            }
        }
    }

    private void efectuarElPago(Jugador jugadorTURNO, Jugador jugador, double aPagar) {
        double money = jugadorTURNO.getDinero();
        money = money - aPagar;
        if (money < 0) {
            System.out.println("************************************************");
            System.out.println("QUIEBRAAAAAAAAAAAAAAAAAAAAA QUIEBRAAAAAAAAAAAAAAA " + jugadorTURNO.getNombre());
            System.out.println("************************************************");

        }
        // se lo sumo al que tiene la calle
        money = jugador.getDinero();
        money += aPagar;
        System.out.println(jugador.getNombre() + " tu dinero es ahora " + money);
        jugador.setDinero(money);
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
