/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovidajavieroi;

import java.util.Random;

/**
 *
 * @author javier
 */
public class Tablero {

    private boolean[][] tablero;
    private int porcentajeCel;

    public Tablero(int dimensiones, int numeroCel) {
        this.tablero = new boolean[dimensiones][dimensiones];
        this.porcentajeCel = numeroCel;
    }

    public boolean[][] getTablero() {
        return tablero;
    }

    public void setTablero(boolean[][] tablero) {
        this.tablero = tablero;
    }

    public double getPorcentajeCel() {
        return porcentajeCel;
    }

    public void setPorcentajeCel(int porcentajeCel) {
        this.porcentajeCel = porcentajeCel;
    }

    public void mostrarTablero() {

    }

    public void asignarCelManual() {

    }

    public void asignarCelAlt() {

        Random alt = new Random();
        int fila;
        int columna;      

        do {
            do {
                fila = alt.nextInt(this.tablero.length);
                columna = alt.nextInt(this.tablero.length);
            } while (this.tablero[fila][columna]);
            this.tablero[fila][columna] = true;

        } while ();

    }
    
    private int calcualrCelAlt(){        
        return (int) ((int)(this.tablero.length^2)*(0.01*this.porcentajeCel));
        
    }

    @Override
    public String toString() {
        return "Tablero " + "\ttablero=" + tablero + ", numeroCelulas=" + porcentajeCel;
    }

}
