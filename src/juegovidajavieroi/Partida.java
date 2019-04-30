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

    private Tablero tabl;
    private int numeroRep;
    private int numeroRepInter;
    private ArrayList<Tablero> listaTabInter;

    public Partida(int dimension, int porcen) {
        this.tabl = new Tablero(dimension, porcen);
        listaTabInter = new ArrayList<>();
    }

    public void tipoGeneracion(int opcion) {
        if (opcion == 1) {
            this.tabl.asignarCelManual();
        } else {
            this.tabl.asignarCelAlt();
        }

    }

    public boolean comprobarRep(Tablero siguiente) {

        if (this.tabl.generacionIgual(siguiente.getTablero())) {
            this.numeroRep++;
            return true;
        }
        this.numeroRep = 0;
        return false;
    }

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

    public void muestraGeneracion() {
        System.out.println("GENERACIÓN " + this.tabl.getNumGeneracion());
        this.tabl.mostrarTablero();
        System.out.println("");
    }

    public boolean crearGeneracion(int i) {
        if (i == 1) {
            muestraGeneracion();
            this.tabl.siguienteGeneracion();
            return true;
        }
        return false;
    }

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
    
    

}
