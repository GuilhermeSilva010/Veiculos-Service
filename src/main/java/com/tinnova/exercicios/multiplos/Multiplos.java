package com.tinnova.exercicios.multiplos;

import java.util.Scanner;

public class Multiplos {

        public static int somaMultiplos(int numeroEntrada) {
            int soma = 0;
            for (int numeroAtual = 1; numeroAtual < numeroEntrada; numeroAtual++) {
                if (numeroAtual % 3 == 0 || numeroAtual % 5 == 0) {
                    soma += numeroAtual;
                }
            }
            return soma;
        }

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            int numeroEntrada = 0;

            while (true) {
                System.out.print("Digite um numero para calcular os multiplos de 3 e 5: ");

                if (scanner.hasNextInt()) {
                    numeroEntrada = scanner.nextInt();
                    if (numeroEntrada >= 0) {
                        break;
                    } else {
                        System.out.println("Por favor, insira um número inteiro não negativo.");
                    }
                } else {
                    System.out.println("Entrada invalida! Digite um numero inteiro.");
                    scanner.next();
                }
            }

            System.out.println("Soma dos multiplos de 3 ou 5 abaixo de " + numeroEntrada + " e: " + somaMultiplos(numeroEntrada));

            scanner.close();
        }
}

