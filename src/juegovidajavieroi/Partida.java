/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovidajavieroi;

import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class Partida {

    private Tablero tabl; // Tablero donde se generan las células
    private int numeroRep;// Nº de veces que se repite la misma generación de forma continuada
    private int numeroRepInter;// Nº de veces que se repite la misma generación
    // de manera intercalada con otra generación
    private ArrayList<Tablero> listaTabInter; // Lista para determinar los casos
    // en los que las generaciones se estancan repitiendose de forma intercalada

    // Constructor parametrizado ( recibe dimensión de la tabla y porcentaje de cel)
    public Partida(int dimension, int porcen) {
        this.tabl = new Tablero(dimension, porcen);
        listaTabInter = new ArrayList<>();
    }

    // Método para determinar si la generación será manual o aleatoria
    public void tipoGeneracion(int opcion) {
        if (opcion == 1) {
            this.tabl.asignarCelManual();
        } else {
            this.tabl.asignarCelAlt();
        }

    }

    // Método para comprobar si la siguiente generación es identica a la anterior
    public boolean comprobarRep(Tablero siguiente) {

        if (this.tabl.generacionIgual(siguiente.getTablero())) {
            this.numeroRep++;
            return true;
        }
        this.numeroRep = 0;
        return false;
    }

    // Método para comprobar si la siguiente generación es identica a la 
    // anterior de la anterior
    public void comprobarRepInter(Tablero inter) {

        if (this.tabl.getNumGeneracion() % 2 == 0) {
            this.listaTabInter.add(inter);

            if (this.tabl.getNumGeneracion() > 2) {

                if (inter.generacionIgual(this.listaTabInter.get(this.listaTabInter.size() - 1).getTablero())) {
                    this.numeroRepInter++;
                } else {
                    this.numeroRepInter = 0;
                }
            }
        }

    }

    // Método para mostrar generación al usuario
    public void muestraGeneracion() {
        System.out.println("GENERACIÓN " + this.tabl.getNumGeneracion());
        this.tabl.mostrarTablero();
        System.out.println("");
    }

    // Método para crear una generación
    public boolean crearGeneracion(int i) {
        if (i == 1) {
            muestraGeneracion();
            this.tabl.siguienteGeneracion();
            return true;
        }
        return false;
    }

    // Getters y setters
    public Tablero getTabl() {
        return tabl;
    }

    public void setTabl(Tablero tabl) {
        this.tabl = tabl;
    }

    public int getNumeroRepInter() {
        return numeroRepInter;
    }

    public void setNumeroRepInter(int numeroRepInter) {
        this.numeroRepInter = numeroRepInter;
    }

    public int getNumeroRep() {
        return numeroRep;
    }

    public void setNumeroRep(int numeroRep) {
        this.numeroRep = numeroRep;
    }

    public ArrayList<Tablero> getListaTabInter() {
        return listaTabInter;
    }

    public void setListaTabInter(ArrayList<Tablero> listaTabInter) {
        this.listaTabInter = listaTabInter;
    }

    // Método toString
    @Override
    public String toString() {
        return "Partida{" + "tabl=" + tabl + ", numeroRep=" + numeroRep + ", numeroRepInter=" + numeroRepInter + ", listaTabInter=" + listaTabInter + '}';
    }
    
    

}
