package com.pablo.blasco.monopoly.service;


import com.pablo.blasco.monopoly.models.Calle;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;


@Component
@AllArgsConstructor
@NoArgsConstructor
public
class FicheroProp {
    Logger logger = LoggerFactory.getLogger(FicheroProp.class);
    File file = null;
    BufferedReader br = null;
    final String CARACTER = ",";
    String nombreProp = "";
    private static final String FILE_PATH = "src/main/resources/calles/propiedades";

    public ArrayList cargaEnMemoria(ArrayList tablero) {
        try {
            br = new BufferedReader(new FileReader(FILE_PATH));
            String linea = "";
            String coste, pagar = "";
            String nombre = "";
            String tipoCalle="";
            int a = 0;
            while ((linea = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linea, CARACTER);
                while (st.hasMoreTokens()) { // consigo mis 3 variables
                    nombreProp = st.nextToken();
                    coste = st.nextToken();
                    pagar = st.nextToken();
                    tipoCalle=st.nextToken();
                    //////
                    //preparado para cargar en Calle
                    Calle calle = new Calle();
                    calle.setNombreCalle(nombreProp);
                    double costeF = Float.valueOf(coste);
                    calle.setCoste(costeF);
                    double pagarF = Float.valueOf(pagar);
                    calle.setPosicion(a);
                    calle.setPagarAlCaer(pagarF);
                    calle.setTipoCalle(tipoCalle);
                    System.out.println("-----> es calle " + nombreProp);
                    tablero.add(calle);
                    a++;
                }
            }
        } catch (FileNotFoundException ex) {
            logger.error("Fichero no encontrado", ex.getMessage());
        } catch (IOException ioe) {
            logger.error("Error:", ioe.getStackTrace());
        }


        return tablero;
    }

}
