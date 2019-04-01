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
public class Tablero {

    private boolean[][] tablero;
    private double numeroCelulas;

    public Tablero(int dimensiones, double numeroCel) {
        this.tablero = new boolean[dimensiones][dimensiones];
        this.numeroCelulas = numeroCel;
    }

    public boolean[][] getTablero() {
        return tablero;
    }

    public void setTablero(boolean[][] tablero) {
        this.tablero = tablero;
    }

    public double getNumeroCelulas() {
        return numeroCelulas;
    }

    public void setNumeroCelulas(double numeroCelulas) {
        this.numeroCelulas = numeroCelulas;
    }   

    public void mostrarTablero() {

    }

    public void asignarCelManual() {

    }

    public void asignarCelAlt() {

    }

    @Override
    public String toString() {
        return "Tablero " + "\ttablero=" + tablero + ", numeroCelulas=" + numeroCelulas;
    }

    

}
