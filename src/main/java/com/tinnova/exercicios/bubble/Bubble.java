package com.tinnova.exercicios.bubble;

public class Bubble {
        public static void bubbleSort(int[] vetor) {
            int n = vetor.length;

            for (int contador = 0; contador < n - 1; contador++) {

                boolean trocou = false;

                trocou = isTrocou(vetor, n, contador, trocou);

                // Se não houve troca, o vetor já está ordenado
                if (!trocou) {
                    break;
                }
            }
        }

    private static boolean isTrocou(int[] vetor, int n, int contador, boolean trocou) {
        for (int adjacente = 0; adjacente < n - contador - 1; adjacente++) {
            if (vetor[adjacente] > vetor[adjacente + 1]) {
                // Troca os elementos
                int temp = vetor[adjacente];
                vetor[adjacente] = vetor[adjacente + 1];
                vetor[adjacente + 1] = temp;

                trocou = true;
            }
        }
        return trocou;
    }

    public static void imprimirVetor(int[] vetor) {
            for (int j : vetor) {
                System.out.print(j + " ");
            }
            System.out.println();
        }

    public static void main(String[] args) {
            int[] vetor = {5, 3, 2, 4, 7, 1, 0, 6};

            System.out.println("Vetor original:");
            imprimirVetor(vetor);

            bubbleSort(vetor);

            System.out.println("Vetor ordenado:");
            imprimirVetor(vetor);
        }
}
