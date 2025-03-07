package com.tinnova.exercicios.votos;

import java.util.Scanner;

public class EleicoesMain {

    // Função principal para testar a classe
    public static void main(String[] args) {
        // Criação do scanner para leitura de dados
        Scanner scanner = new Scanner(System.in);

        // Solicitar os dados do usuário com validação para aceitar apenas números inteiros
        int totalEleitores = validacaoNumerosEntrada(scanner, "Digite o total de eleitores: ");
        int votosValidos = validacaoNumerosEntrada(scanner, "Digite o numero de votos validos: ");
        int votosEmBranco = validacaoNumerosEntrada(scanner, "Digite o numero de votos em branco: ");
        int votosNulos = validacaoNumerosEntrada(scanner, "Digite o numero de votos nulos: ");

        Eleicoes eleicoes = new Eleicoes(totalEleitores, votosValidos, votosEmBranco, votosNulos);

        // Exibe os resultados dos percentuais
        System.out.println("Percentual de votos validos: " + eleicoes.calcularPercentualVotosValidos() + "%");
        System.out.println("Percentual de votos em branco: " + eleicoes.calcularPercentualVotosEmBranco() + "%");
        System.out.println("Percentual de votos nulos: " + eleicoes.calcularPercentualVotosNulos() + "%");

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
