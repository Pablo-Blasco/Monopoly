package com.pablo.blasco.monopoly.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Compra {
    private Jugador jugador ;
    private Calle calle ;
    private int posicion;
    private String mensajeCompra;
}
