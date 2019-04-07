/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juegovidajavieroi;

import java.util.Random;
import java.util.Scanner;

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
        Scanner tec = new Scanner(System.in);
        int fila;
        int columna;
        int numCel = calcularCelAlt();
        int veces = 0;

        do {
            do {
                System.out.println("Introduce la fila donde insertar la célula: ");
                fila = tec.nextInt() - 1;
                while (fila > this.tablero.length || fila < this.tablero.length) {
                    System.out.println("La fila introducida no existe");
                    System.out.println("Introduce la fila donde insertar la célula: ");
                    fila = tec.nextInt() - 1;
                }

                System.out.println("Introduce la columna donde insertar la célula");
                columna = tec.nextInt() - 1;
                while (fila > this.tablero.length || fila < this.tablero.length) {
                    System.out.println("La columna introducida no existe");
                    System.out.println("Introduce la columna donde insertar la célula: ");
                    fila = tec.nextInt() - 1;
                }
            } while (this.tablero[fila][columna]);
            this.tablero[fila][columna] = true;
            veces++;
        } while (veces != numCel);

    }

    public void asignarCelAlt() {

        Random alt = new Random();
        int fila;
        int columna;
        int numCel = calcularCelAlt();
        int veces = 0;

        do {
            do {
                fila = alt.nextInt(this.tablero.length);
                columna = alt.nextInt(this.tablero.length);
            } while (this.tablero[fila][columna]);
            this.tablero[fila][columna] = true;
            veces++;

        } while (veces != numCel);

    }

    private int calcularCelAlt() {
        return (int) ((int) (this.tablero.length ^ 2) * (0.01 * this.porcentajeCel));

    }

    public void siguienteGeneracion() {

        int celulasVecinas = 0;

        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {

                if(i==0 && (j!=0 || j!= this.tablero.length)){
                    celulasVecinas = celFilaSup(i,j);                    
                } else if (j==0 && (i!=0 || i!=this.tablero.length)){
                    celulasVecinas = celColIzq(i,j);
                } else if (i == this.tablero.length && (j!=0 || j!=this.tablero.length)){
                    celulasVecinas = celFilaInf(i,j);
                } else if (j == this.tablero.length && (i!=0 || i!= this.tablero.length)){
                    celulasVecinas = celColDer(i,j);
                } else if (!((i==0 && j==0) || (i==this.tablero.length && j==this.tablero.length)
                        || (i==this.tablero.length && j==0) || (i==0 && j==this.tablero.length))){
                    celulasVecinas = celEstandar(i,j);
                }
                
            }     
            

        }

    }

    private int celFilaSup(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;
    }

    private int celFilaInf(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila - 1][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila - 1][columna + 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celColIzq(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila - 1][columna + 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celColDer(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila - 1][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna - 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celEstandar(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila - 1][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila - 1][columna + 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna - 1]) {
            celulasVecinas++;
        } else if (this.tablero[fila + 1][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    @Override
    public String toString() {
        return "Tablero " + "\ttablero=" + tablero + ", numeroCelulas=" + porcentajeCel;
    }

}
