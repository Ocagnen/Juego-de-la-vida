/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovidajavieroi;

import java.util.Scanner;

/**
 *
 * @author javier
 */
public class Vista {

    public static Scanner tec = new Scanner(System.in);

    public static Comando mostrarMenu() {

        int respuesta;

        System.out.println("JUEGO DE LA VIDA");

        do {

            System.out.println("----------------------------");
            System.out.println("¿Qué deseas hacer?");
            System.out.println("1.Partida nueva");
            System.out.println("2.Cargar partida");
            respuesta = tec.nextInt();

        } while (respuesta > 2 || respuesta < 1);

    }
    
    public static int elegirTablero(){
        
        System.out.println("¿Qué dimensiones desea darle al tablero? (N x N)");
        
    }

}
