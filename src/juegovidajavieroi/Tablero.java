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

        System.out.println("-------------------------------------------");
        System.out.println("");
        int veces = 0;

        do {
            System.out.println("");
            for (int i = 0; i < this.tablero.length; i++) {
                if (this.tablero[veces][i]) {
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
        return (int) ((int) (this.tablero.length * this.tablero.length) * (0.01 * this.porcentajeCel));

    }

    public void siguienteGeneracion() {

        int celulasVecinas;

        for (int i = 0; i < this.tablero.length; i++) {
            for (int j = 0; j < this.tablero.length; j++) {
                System.out.println("");
                System.out.println("fila "+i+"Columa "+j);
                celulasVecinas = 0;
                if (i == 0 && (j != 0 && j != this.tablero.length - 1)) {
                    celulasVecinas = celFilaSup(i, j);
                    System.out.println("primer"+celulasVecinas);
                } else if (j == 0 && (i != 0 && i != this.tablero.length - 1)) {
                    celulasVecinas = celColIzq(i, j);
                    System.out.println("segundo"+celulasVecinas);
                } else if (i == this.tablero.length - 1 && (j != 0 && j != this.tablero.length - 1)) {
                    celulasVecinas = celFilaInf(i, j);
                    System.out.println("tercer"+celulasVecinas);
                } else if (j == this.tablero.length - 1 && (i != 0 && i != this.tablero.length - 1)) {
                    celulasVecinas = celColDer(i, j);
                    System.out.println("cuarto"+celulasVecinas);
                } else if (!((i == 0 && j == 0) || (i == this.tablero.length - 1 && j == this.tablero.length - 1)
                        || (i == this.tablero.length - 1 && j == 0) || (i == 0 && j == this.tablero.length - 1))) {
                    celulasVecinas = celEstandar(i, j);
                    System.out.println("quinto"+celulasVecinas);
                }
                
                System.out.println(celulasVecinas);
                this.tablero[i][j]=determinarEstadoCel(celulasVecinas);

            }

        }

    }

    public int celFilaSup(int fila, int columna) {
        int celulasVecinas = 0;
        System.out.println("LA FILA PASADA ES "+fila);
        System.out.println("LA COLUMNA PASADA ES"+columna);
        
        System.out.println("ES TRUE?:"+this.tablero[fila][columna - 1]);
        if (this.tablero[fila][columna - 1]) {
            System.out.println("uno"+celulasVecinas);
            celulasVecinas = celulasVecinas +1;
        }
        if (this.tablero[fila][columna + 1]) {
                       // System.out.println("dos"+celulasVecinas);

            celulasVecinas = celulasVecinas +1;
        }
        if (this.tablero[fila + 1][columna]) {
                       // System.out.println("tres"+celulasVecinas);

            celulasVecinas = celulasVecinas +1;
        }
        if (this.tablero[fila + 1][columna - 1]) {
                        //System.out.println("cuatro"+celulasVecinas);

            celulasVecinas = celulasVecinas +1;
        }
        if (this.tablero[fila + 1][columna + 1]) {
                        //System.out.println("cinco"+celulasVecinas);

            celulasVecinas = celulasVecinas +1;
        }
        System.out.println("CLUAS TOTALES"+celulasVecinas);
        return celulasVecinas;
    }

    private int celFilaInf(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna + 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celColIzq(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna + 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celColDer(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna - 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private int celEstandar(int fila, int columna) {
        int celulasVecinas = 0;

        if (this.tablero[fila - 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila - 1][columna + 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila][columna + 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna - 1]) {
            celulasVecinas++;
        }
        if (this.tablero[fila + 1][columna + 1]) {
            celulasVecinas++;
        }

        return celulasVecinas;

    }

    private boolean determinarEstadoCel(int celulasCerca) {
        boolean celula = false;
        
        switch (celulasCerca) {
            case 0:
            case 1:
                celula = false;
                break;
            case 2:
                break;
            case 3:
                celula = true;
                break;
            default:
                celula = false;
        }

        return celula;
    }

    @Override
    public String toString() {
        return "Tablero " + "\ttablero=" + tablero + ", numeroCelulas=" + porcentajeCel;
    }

}
