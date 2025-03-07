package com.tinnova.exercicios.votos;

public class Eleicoes {

        private int totalEleitores;
        private int votosValidos;
        private int votosEmBranco;
        private int votosNulos;

        // Construtor para inicializar os dados
        public Eleicoes(int totalEleitores, int votosValidos, int votosEmBranco, int votosNulos) {
            this.totalEleitores = totalEleitores;
            this.votosValidos = votosValidos;
            this.votosEmBranco = votosEmBranco;
            this.votosNulos = votosNulos;
        }

        // Método para calcular o percentual de votos válidos
        public double calcularPercentualVotosValidos() {
            if (totalEleitores == 0) {
                return 0;
            }
            return (double) votosValidos / totalEleitores * 100;
        }

        // Método para calcular o percentual de votos em branco
        public double calcularPercentualVotosEmBranco() {
            if (totalEleitores == 0) {
                return 0;
            }
            return (double) votosEmBranco / totalEleitores * 100;
        }

        // Método para calcular o percentual de votos nulos
        public double calcularPercentualVotosNulos() {
            if (totalEleitores == 0) {
                return 0;
            }
            return (double) votosNulos / totalEleitores * 100;
        }

}
