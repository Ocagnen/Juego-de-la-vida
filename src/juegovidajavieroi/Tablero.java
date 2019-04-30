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

    private Celula[][] tablero; // Matriz de clase Célula que hace función de tablero
    private int porcentajeCel; // % de células que habrá vivas en la 1ª gen 
    private int numGeneracion; // Nº de la generación de células

    // Constructor al que se le pasan las dimensiones (X * X) y el porcentaje
    // de células vivas en la 1ª gen (generación 0)
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

    // Método para copiar tablero
    public Tablero copiarTab() {

        Tablero aux = new Tablero(this.getTablero().length, this.porcentajeCel);

        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {
                if (this.tablero[i][j].isEstado()) {
                    aux.tablero[i][j].setEstado(true);
                } else {
                    aux.tablero[i][j].setEstado(false);
                }
            }

        }

        return aux;

    }

    // Método que determina si una generación es igual a la otra. Compara 
    // dos matrices para ver si los valores de ambas son identicos
    public boolean generacionIgual(Celula[][] cel) {

        for (int i = 0; i < cel.length; i++) {
            for (int j = 0; j < cel.length; j++) {

                if (this.tablero[i][j].isEstado() != cel[i][j].isEstado()) {
                    return false;
                }

            }

        }

        return true;

    }

    // Getters y setters 
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

    // Método para mostrar el tablero ( X si una célula está viva, un espacio 
    // vacío si está muerta)
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
    
    // Método para asignar celulas de forma manual. Primero indicaremos la
    // columna y luego la fila donde ubicar la célula viva.
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
                while (fila > this.tablero.length - 1 || fila < 0) {
                    System.out.println("La fila introducida no existe");
                    System.out.println("Introduce la fila donde insertar la célula: ");
                    fila = tec.nextInt() - 1;
                }

                System.out.println("Introduce la columna donde insertar la célula");
                columna = tec.nextInt() - 1;
                while (columna > this.tablero.length - 1 || columna < 0) {
                    System.out.println("La columna introducida no existe");
                    System.out.println("Introduce la columna donde insertar la célula: ");
                    columna = tec.nextInt() - 1;
                }
            } while (this.tablero[fila][columna].isEstado());
            this.tablero[fila][columna].setEstado(true);
            veces++;
        } while (veces != numCel);

    }
    
    // Método para que en la primera generación las células vivas estén en
    // posiciones aleatorias de la matriz
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

            if (numCel != 0) {
                this.tablero[fila][columna].setEstado(true);
                veces++;
            }

        } while (veces != numCel);

    }

    // Método para determinar el número de células vivas que habrá en función 
    // del tamaño y el procentaje de la matriz.
    private int calcularCelAlt() {
        return (int) ((int) (this.tablero.length * this.tablero.length) * (0.01 * this.porcentajeCel));

    }

    // Método para generar siguiente generación de células
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

    // Método para determinar las células adyacentes a una célula que 
    // se encuentre en la fila superior
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

    // Método para determinar las células adyacentes a una célula que 
    // se encuentre en la fila inferior
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

    // Método para determinar las células adyacentes a una célula que 
    // se encuentre en la columna izquierda
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

    // Método para determinar las células adyacentes a una célula que 
    // se encuentre en la columna derecha
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

    // Método para determinar las células adyacentes a una célula que no 
    // se encuentre en el borde de la matriz
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

    // Método para determinar el estado de una célula (viva/muerta) en
    // función de la cantidad de celulas adyacentes que tenga
    private boolean determinarEstadoCel(int celulasCerca, Celula cel) {
        boolean celula;

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

    // Método para matar o revivir células de la matriz 
    private void matarRevivirCel() {
        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {
                this.tablero[i][j].setEstado(determinarEstadoCel(this.tablero[i][j].getCelulasAdy(),
                        this.tablero[i][j]));

            }

        }
    }

    // Método toString
    @Override
    public String toString() {
        return "Tablero{" + "tablero=" + tablero + ", porcentajeCel=" + porcentajeCel + '}';
    }

}
