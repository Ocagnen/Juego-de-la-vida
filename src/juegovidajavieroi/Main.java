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

        Partida p1 = new Partida(Vista.elegirTablero(), Vista.elegirPorcentajeCel());

        p1.tipoGeneracion(Vista.elegirManualAlt());

        boolean seguir;
        Tablero tAux;
        

        do {
            p1.muestraGeneracion();           

            tAux = p1.getTabl().copiarTab();

            seguir = p1.crearGeneracion(Vista.elegirContinuar());

            p1.comprobarRep(tAux);
            p1.comprobarRepInter(tAux);
            
            if((p1.getNumeroRep()==3) || p1.getNumeroRepInter()==2){
                p1.muestraGeneracion();
            }

        } while (seguir && (p1.getNumeroRep() != 3)&& 
                (p1.getNumeroRepInter() != 2));

    }

}
