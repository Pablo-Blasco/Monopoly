package com.pablo.blasco.monopoly.controller;


import com.pablo.blasco.monopoly.models.*;
import com.pablo.blasco.monopoly.service.FicheroProp;
import com.pablo.blasco.monopoly.service.GestorCompraPaga;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;

@Controller
public class JuegoController {
    private final Double DINERO_INICIAL = 300.0;
    private FicheroProp ficheroProp = new FicheroProp();
    private Logger logger = LoggerFactory.getLogger(JuegoController.class);
    private static Juego game;

    @GetMapping("/")
    public String inicio(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        return "inicio";
    }

    @PostMapping(value = "/iniciajuego")
    public String inicioJuego(Jugadores jugadorForm, Model model) {
        ArrayList tablero = new ArrayList();
        logger.debug(tablero.toString());
        tablero = ficheroProp.cargaEnMemoria(tablero);
        ArrayList<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador(jugadorForm.getNombreJugador1(), 1, new ArrayList(), true, true);
        jugador1.setDinero(this.DINERO_INICIAL);
        jugadores.add(jugador1);
        Jugador jugador2 = new Jugador(jugadorForm.getNombreJugador2(), 2, new ArrayList(), true, false);
        jugador2.setDinero(this.DINERO_INICIAL);
        jugadores.add(jugador2);
        Jugador jugador3 = new Jugador(jugadorForm.getNombreJugador3(), 3, new ArrayList(), true, false);
        jugador3.setDinero(this.DINERO_INICIAL);
        jugadores.add(jugador3);
        Jugador jugador4 = new Jugador(jugadorForm.getNombreJugador4(), 4, new ArrayList(), true, false);
        jugador4.setDinero(this.DINERO_INICIAL);
        jugadores.add(jugador4);
        model.addAttribute("jugadores", jugadores);
        model.addAttribute("dado",0);
        game = new Juego(jugadores, tablero, true);
        game.jugar();
        return "juego";
    }

    @GetMapping("/tirardado")
    public String tiradados(@RequestParam(name = "numjugador", required = true) int numJugador, Model model) {
        if(game.getJugadores().get((numJugador - 1)).isJugando()) {
            int dados = (int) (Math.random() * 12) + 1;
            game.jugar(game.getJugadores().get((numJugador - 1)), dados);
            GestorCompraPaga gestorCompraPaga = new GestorCompraPaga();
            if (gestorCompraPaga.esEspecial(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion())) {
                game.getJugadores().get(jugadorSiguiente(numJugador)).setEsMiTurno(true);
                game.getJugadores().get(numJugador - 1).setEsMiTurno(false);
                model.addAttribute("dado", 0);
                model.addAttribute("calle", gestorCompraPaga.devuelveEspecial(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion()));
            } else if (gestorCompraPaga.esImpuesto(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion())) {
                gestorCompraPaga.pagaImpuesto(game.getTablero(), game.getJugadores().get(numJugador - 1), game.getJugadores().get(numJugador - 1).getPosicion());
                game.getJugadores().get(jugadorSiguiente(numJugador)).setEsMiTurno(true);
                game.getJugadores().get(numJugador - 1).setEsMiTurno(false);
                model.addAttribute("dado", 0);
                model.addAttribute("calle", gestorCompraPaga.devuelveEspecial(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion()));
            } else if (gestorCompraPaga.estaComprada(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion())) {
                gestorCompraPaga.quienDuenyo(game.getJugadores().get(numJugador - 1).getPosicion(), game.getJugadores().get(numJugador - 1), game.getCompraArray());
                game.getJugadores().get(jugadorSiguiente(numJugador)).setEsMiTurno(true);
                game.getJugadores().get(numJugador - 1).setEsMiTurno(false);
                model.addAttribute("calle", gestorCompraPaga.devuelveCalle(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion()));
                model.addAttribute("dado", 0);

            } else {
                model.addAttribute("calle", gestorCompraPaga.devuelveCalle(game.getTablero(), game.getJugadores().get(numJugador - 1).getPosicion()));
                model.addAttribute("dado", dados);
            }

        }else {
            game.getJugadores().get(jugadorSiguiente(numJugador)).setEsMiTurno(true);
            model.addAttribute("dado", 0);
        }
        model.addAttribute("compras",game.getCompraArray());
        model.addAttribute("jugadores", game.getJugadores());

        return "juego";
    }

    @GetMapping("/compra")
    public String compra(@RequestParam(name = "numjugador", required = true) int numJugador, @RequestParam(name = "compra", required = true) String compra, Model model) {

        if (compra.equals("s")) {
            GestorCompraPaga gestorCompraPaga = new GestorCompraPaga();
            model.addAttribute("mensaje",gestorCompraPaga.compra(game.getTablero(), game.getCompraArray(), game.getJugadores().get(numJugador - 1), game.getJugadores().get(numJugador - 1).getPosicion(), game.getBanca()));
            game.getTablero().get(game.getJugadores().get(numJugador - 1).getPosicion()-1).setEstaCompr(true);
        }
        game.getJugadores().get(jugadorSiguiente(numJugador)).setEsMiTurno(true);
        game.getJugadores().get(numJugador - 1).setEsMiTurno(false);
        model.addAttribute("compras",game.getCompraArray());
        model.addAttribute("jugadores", game.getJugadores());
        model.addAttribute("dado", 0);
        return "juego";
    }
    public int  jugadorSiguiente(int numjugador) {

      if(numjugador==4) {
          for (int i = 0; i <3; i++) {
            if (game.getJugadores().get(i).isJugando())
                return game.getJugadores().get(i).getNumJuga()-1;

          }
      }else {
          for (int i = (numjugador); i < 4; i++) {
              if (game.getJugadores().get(i).isJugando())
                  return game.getJugadores().get(i).getNumJuga()-1;
          }
      }
        return 0;
    }
}


