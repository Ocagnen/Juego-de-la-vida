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

        System.out.println("EL JUEGO DE LA VIDA");
        System.out.println("-----------------------------");

        // Creamos una partida usando los datos elegidos por usuario
        Partida p1 = new Partida(Vista.elegirTablero(), Vista.elegirPorcentajeCel());

        // Determinamos tipo de generación de acuerdo al usuario
        p1.tipoGeneracion(Vista.elegirManualAlt());

        // Atributos que nos determinaran si se generará otra generación o no
        boolean seguir; // Mientras sea true se genera otra generación
        Tablero tAux; // Guarda tablero de generación anterior para comparar con siguiente
        
        // Bucle para generar generaciones de células
        do {
            p1.muestraGeneracion();           
            
            // Crea un tablero que es copia del de la generación actual
            // para compararlo con la siguiente generación y determinar si se
            // están repitiendo
            tAux = p1.getTabl().copiarTab();
            
            // Usario determina si crear otra generación o finalizar
            seguir = p1.crearGeneracion(Vista.elegirContinuar());

            // Comprobamos si se están repitiendo generaciones
            p1.comprobarRep(tAux);
            p1.comprobarRepInter(tAux);
            
            // Si se han repetido las veces determinadas mostraremos la
            // última generación que se ha generado antes de salir del bucle
            if((p1.getNumeroRep()==3) || p1.getNumeroRepInter()==2){
                p1.muestraGeneracion();
            }

        } while (seguir && (p1.getNumeroRep() != 3)&& 
                (p1.getNumeroRepInter() != 2));

    }

}
