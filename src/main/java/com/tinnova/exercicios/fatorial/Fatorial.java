package com.tinnova.exercicios.fatorial;

import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numeroEntrada = 0;

        while (true) {
            System.out.print("Digite um numero inteiro para calcular o fatorial: ");

            if (scanner.hasNextInt()) {
                numeroEntrada = scanner.nextInt();
                if (numeroEntrada >= 0) {
                    break;
                } else {
                    System.out.println("Por favor, insira um numero inteiro nao negativo.");
                }
            } else {
                System.out.println("Entrada invalida! Digite um numero inteiro.");
                scanner.next();
            }
        }

        long resultado = calcularFatorial(numeroEntrada);

        System.out.println("O fatorial de " + numeroEntrada + " e: " + resultado);

        scanner.close();
    }

    public static long calcularFatorial(int numeroEntrada) {
        long fatorial = 1;
        for (int contadorFatorial = 2; contadorFatorial <= numeroEntrada; contadorFatorial++) {
            fatorial *= contadorFatorial;
        }
        return fatorial;
    }
}

