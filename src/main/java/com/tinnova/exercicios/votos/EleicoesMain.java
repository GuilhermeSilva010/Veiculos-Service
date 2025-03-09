package com.tinnova.exercicios.votos;

import java.util.Scanner;

public class EleicoesMain {

    // Função principal para testar a classe
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int totalEleitores, votosValidos, votosEmBranco, votosNulos;

        while (true) {
            System.out.print("Digite o total de eleitores: ");
            totalEleitores = scanner.nextInt();

            System.out.print("Digite o número de votos válidos: ");
            votosValidos = scanner.nextInt();

            System.out.print("Digite o número de votos em branco: ");
            votosEmBranco = scanner.nextInt();

            System.out.print("Digite o número de votos nulos: ");
            votosNulos = scanner.nextInt();

            int totalVotos = votosValidos + votosEmBranco + votosNulos;

            if (totalVotos > totalEleitores) {
                System.out.println("Erro! O total de votos (" + totalVotos + ") excede o número de eleitores (" + totalEleitores + ").");
                System.out.println("Por favor, insira os dados novamente.\n");
            } else {
                break;
            }
        }

        Eleicoes eleicao = new Eleicoes(totalEleitores, votosValidos, votosEmBranco, votosNulos);

        System.out.printf("Percentual de votos válidos: %.2f%%\n", eleicao.calcularPercentualVotosValidos());
        System.out.printf("Percentual de votos em branco: %.2f%%\n", eleicao.calcularPercentualVotosEmBranco());
        System.out.printf("Percentual de votos nulos: %.2f%%\n", eleicao.calcularPercentualVotosNulos());

        scanner.close();
    }

    private static int validacaoNumerosEntrada(Scanner scanner, String mensagem) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero >= 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Por favor, insira um numero inteiro positivo.");
                }
            } else {
                System.out.println("Entrada invalida! Por favor, insira um numero inteiro.");
                scanner.next();
            }
        }
        return numero;
    }
}
