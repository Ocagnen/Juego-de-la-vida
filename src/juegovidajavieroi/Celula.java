/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovidajavieroi;

/**
 *
 * @author Javier
 */
public class Celula {
    
    private boolean estado;
    private int celulasAdy;

    public Celula(boolean estado, int celulasAdy) {
        this.estado = estado;
        this.celulasAdy = celulasAdy;
    }
    
    public Celula(){
        this.estado = false;
        this.celulasAdy = 0;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCelulasAdy() {
        return celulasAdy;
    }

    public void setCelulasAdy(int celulasAdy) {
        this.celulasAdy = celulasAdy;
    }

    @Override
    public String toString() {
        return "Celula{" + "estado=" + estado + ", celulasAdy=" + celulasAdy + '}';
    }
    
    
    
}
