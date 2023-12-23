package com.pablo.blasco.monopoly.controller;


import com.pablo.blasco.monopoly.models.Calle;
import com.pablo.blasco.monopoly.models.Juego;
import com.pablo.blasco.monopoly.models.Jugador;
import com.pablo.blasco.monopoly.models.Jugadores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class JuegoController {

    @GetMapping("/")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {

        return "inicio";
    } @PostMapping("/iniciojuego")
    public String iniciJuego(@ModelAttribute Jugadores jugadorForm, Model model) {
        ArrayList<Jugador> jugadores=new ArrayList<>();
        jugadores.add(jugadorForm.getNombreJugador1());
        jugadores.add(jugadorForm.getNombreJugador2());
        jugadores.add(jugadorForm.getNombreJugador3());
        jugadores.add(jugadorForm.getNombreJugador4());
        ArrayList<Calle> tablero=new ArrayList<>();
        Juego game=new Juego(jugadores,tablero,true);
        model.addAttribute("jugadore",jugadores);
        return "juego";
    }

}
