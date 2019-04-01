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

    public Tablero(int n) {        
        
        this.tablero = new boolean[n][n];
    }
    
    public boolean[][] getTablero() {
        return tablero;
    }

    public void setTablero(boolean[][] tablero) {
        this.tablero = tablero;
    }
    
    public void mostrarTablero(){
        
    }

    @Override
    public String toString() {
        return "Tablero" + "tablero=" + tablero ;
    }
    
    
}
