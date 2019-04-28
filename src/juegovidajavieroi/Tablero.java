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

    private Celula[][] tablero;
    private int porcentajeCel;
    private int numGeneracion;

    public Tablero(int dimensiones, int numeroCel) {
        this.tablero = new Celula[dimensiones][dimensiones];

        for (int i = 0; i < dimensiones; i++) {
            for (int j = 0; j < dimensiones; j++) {
                this.tablero[i][j] = new Celula();
            }
        }

        this.porcentajeCel = numeroCel;
        this.numGeneracion = 0;
    }

    public boolean generacionIgual(Celula[][] cel){
        
        for (int i = 0; i < cel.length; i++) {
            for (int j = 0; j < cel.length; j++) {
                
                if(this.tablero[i][j]!=cel[i][j]){
                    return false;
                }
                
            }
            
        }
        
        return true;
        
    }
    
    public Celula[][] getTablero() {
        return tablero;
    }

    public void setTablero(Celula[][] tablero) {
        this.tablero = tablero;
    }

    public double getPorcentajeCel() {
        return porcentajeCel;
    }

    public void setPorcentajeCel(int porcentajeCel) {
        this.porcentajeCel = porcentajeCel;
    }

    public int getNumGeneracion() {
        return numGeneracion;
    }

    public void setNumGeneracion(int numGeneracion) {
        this.numGeneracion = numGeneracion;
    }

    public void mostrarTablero() {

        System.out.println("-------------------------------------------");
        System.out.println("");
        int veces = 0;

        do {
            System.out.println("");
            for (int i = 0; i < this.tablero.length; i++) {
                if (this.tablero[veces][i].isEstado()) {
                    System.out.print("|X|\t");
                } else {
                    System.out.print("||\t");
                }
            }

            System.out.println("");
            veces++;
        } while (veces != this.tablero.length);

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
            } while (this.tablero[fila][columna].isEstado());
            this.tablero[fila][columna].setEstado(true);
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
            } while (this.tablero[fila][columna].isEstado());
            this.tablero[fila][columna].setEstado(true);
            veces++;

        } while (veces != numCel);

    }

    private int calcularCelAlt() {
        return (int) ((int) (this.tablero.length * this.tablero.length) * (0.01 * this.porcentajeCel));

    }

    public void siguienteGeneracion() {

        int celulasVecinas;

        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {
                celulasVecinas = 0;
                if (i == 0 && (j != 0 && j != this.tablero.length - 1)) {
                    celulasVecinas = celFilaSup(i, j);
                } else if (j == 0 && (i != 0 && i != this.tablero.length - 1)) {
                    celulasVecinas = celColIzq(i, j);
                } else if (i == this.tablero.length - 1 && (j != 0 && j != this.tablero.length - 1)) {
                    celulasVecinas = celFilaInf(i, j);
                } else if (j == this.tablero.length - 1 && (i != 0 && i != this.tablero.length - 1)) {
                    celulasVecinas = celColDer(i, j);
                } else if (!((i == 0 && j == 0) || (i == this.tablero.length - 1 && j == this.tablero.length - 1)
                        || (i == this.tablero.length - 1 && j == 0) || (i == 0 && j == this.tablero.length - 1))) {
                    celulasVecinas = celEstandar(i, j);
                }

                this.tablero[i][j].setCelulasAdy(celulasVecinas);

            }

        }

        matarRevivirCel();
        this.numGeneracion = this.numGeneracion + 1;

    }

    private int celFilaSup(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila][columna - 1].isEstado()) {
            celulasVecinas = celulasVecinas + 1;
        }
        if (this.tablero[fila][columna + 1].isEstado()) {
            celulasVecinas = celulasVecinas + 1;
        }
        if (this.tablero[fila + 1][columna].isEstado()) {
            celulasVecinas = celulasVecinas + 1;
        }
        if (this.tablero[fila + 1][columna - 1].isEstado()) {
            celulasVecinas = celulasVecinas + 1;
        }
        if (this.tablero[fila + 1][columna + 1].isEstado()) {
            celulasVecinas = celulasVecinas + 1;
        }

        return celulasVecinas;
    }

    private int celFilaInf(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna + 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna + 1].isEstado()) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celColIzq(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna + 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna + 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna + 1].isEstado()) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celColDer(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna - 1].isEstado()) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celEstandar(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna + 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna + 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna - 1].isEstado()) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna + 1].isEstado()) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private boolean determinarEstadoCel(int celulasCerca, Celula cel) {
        boolean celula = false;

        switch (celulasCerca) {
            case 0:
            case 1:
                celula = false;
                break;
            case 2:
                celula = cel.isEstado();
                break;
            case 3:
                celula = true;
                break;
            default:
                celula = false;
        }

        return celula;
    }

    private void matarRevivirCel() {
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {

                this.tablero[i][j].setEstado(determinarEstadoCel(this.tablero[i][j].getCelulasAdy(),
                        this.tablero[i][j]));

            }

        }
    }

    @Override
    public String toString() {
        return "Tablero{" + "tablero=" + tablero + ", porcentajeCel=" + porcentajeCel + '}';
    }

}
