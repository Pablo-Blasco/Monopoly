package com.pablo.blasco.monopoly.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Calle {

    private String nombreCalle;
    private int posicion;
    private double coste ;
    private boolean estaCompr;
    private double pagarAlCaer;
    private String tipoCalle;
    private Jugador miDuenyo;
    @Override
    public String toString() {
        return "CallesProp{" + "nombreCalle=" + nombreCalle + ", posicion=" + posicion + ", coste=" + coste + ", estaCompr=" + estaCompr + '}';
    }
}
