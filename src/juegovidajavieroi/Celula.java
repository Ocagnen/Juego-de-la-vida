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
    
    private boolean estado; // Determina si está viva o muerta
    private int celulasAdy; // Células adyacentes en la matriz

    // Constructor parametrizado
    public Celula(boolean estado, int celulasAdy) {
        this.estado = estado;
        this.celulasAdy = celulasAdy;
    }
    
    //Constructor por defecto
    public Celula(){
        this.estado = false;
        this.celulasAdy = 0;
    }

    //Getters y setters
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

    // ToString
    @Override
    public String toString() {
        return "Celula{" + "estado=" + estado + ", celulasAdy=" + celulasAdy + '}';
    }
    
    
    
}
