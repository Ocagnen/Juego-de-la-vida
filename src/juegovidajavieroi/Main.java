/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovidajavieroi;

/**
 *
 * @author javier
 */
public class Main {

    public static void main(String[] args) {

        Tablero tab = new Tablero(7, 20);

        tab.asignarCelAlt();

        tab.mostrarTablero();       
        
        tab.siguienteGeneracion();
        
        tab.mostrarTablero();
        

    }

}
