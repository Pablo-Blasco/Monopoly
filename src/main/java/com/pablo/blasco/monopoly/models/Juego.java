package com.pablo.blasco.monopoly.models;


import com.pablo.blasco.monopoly.service.GestorCompraPaga;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Juego {
 	 final int NCASILLAS = 30;
         final int CARCEL = 5;
	 //final double DINEROJUG = 50.0f;
	 //boolean listadosB=true;  
	
	ArrayList<Calle> tablero = null;
	ArrayList<Jugador> jugadores = null;
	ArrayList<Compra> compraArray=null;
	
	Scanner sca = null;
	 
 public Juego(ArrayList jugadores,ArrayList tablero, boolean listadosB)
 {
	this.jugadores = jugadores;
	this.tablero = tablero;
	compraArray = new ArrayList();
	listadosB = listadosB;
  
 }
 
 public void jugar()
 {
         GestorCompraPaga gcp = new GestorCompraPaga();
         
	 sca = new Scanner(System.in);
	 Jugador jugador=null;
	 int num = 0;
	 String nombre = "";
	 double dinero = 0.0f;
	 int dados = 0;
	 Banca banca= null;
	 
	 banca = new Banca(1000.f,"Inicio",false);
	 
	 //bancaArray.add(banca);
	 
	 System.out.println("\n########JUGANDO en un tablero " + tablero.size());
	 sca.nextLine();
	 while (true)
	 {
             Iterator<Jugador> itJug = jugadores.iterator();
	 
	 while (itJug.hasNext())
	 {
             
		 jugador = (Jugador) itJug.next();
		 
		 jugador.setEsMiTurno(true);
		 num = jugador.getNumJuga();
		 nombre = jugador.getNombre();
		 dinero= jugador.getDinero();
		 System.out.println("######## Turno de "+ nombre + " " + num+1 + " "+dinero+" tira dados" );
		 dados = (int)(Math.random()*12)+1;
                 //dados = Integer.parseInt(sca.nextLine()); // truco para meter lo que quiero que salga (PRUEBAS)
		 System.out.println("ooooooo Resultado "+ dados);
		 int pos = jugador.getPosicion();
		 pos = pos + dados;
		 
		 if (pos >= NCASILLAS)
		 {
			 System.out.println("Pasas por el origen.Recibes 20e " );
			 float dineroAhora = (float) jugador.getDinero();
			 jugador.setDinero(dineroAhora+20.0f);
	         ///// HACER LOS CALCULOS PARA EL PASO
                         pos = pos-NCASILLAS;
			 
		 }
		 
                 jugador.setPosicion(pos);
			 System.out.println("Estas en la casilla " + pos);
		         
                         if (pos==0)
                         {
                             System.out.println("Estas en la casilla ORIGEN, no hay mas que hacer");   
                         }
			 else
                         {
			 gcp.compraOPaga(tablero, compraArray, jugador, pos, banca);
                         gcp.listaCompras(compraArray);     
                         }       
		 }
         
	 System.out.println("siguiente vueltaaaaaaaaaaaaaaaaaaa");
	 System.out.println("Si quieres salir pulsa X, si no una tecla");
	 String siono=sca.nextLine();
	 if (siono.charAt(0)=='X')
		 System.exit(-1);
        
         
	 }
 }
 
 
}
