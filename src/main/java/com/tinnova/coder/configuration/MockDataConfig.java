package com.tinnova.coder.configuration;

import com.tinnova.coder.domain.Veiculo;
import com.tinnova.coder.repository.VeiculoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDataConfig {

    @Bean
    public CommandLineRunner dataLoader(VeiculoRepository veiculoRepository) {
        return args -> {
            // Verificar se os dados já existem na tabela antes de inserir
            if (veiculoRepository.count() == 0) {
                Veiculo veiculo1 = new Veiculo();
                veiculo1.setMarca("Fiat");
                veiculo1.setAno(2021);
                veiculo1.setCor("Preto");

                Veiculo veiculo2 = new Veiculo();
                veiculo2.setMarca("Chevrolet");
                veiculo2.setAno(2020);
                veiculo2.setCor("Branco");

                Veiculo veiculo3 = new Veiculo();
                veiculo3.setMarca("Volkswagen");
                veiculo3.setAno(2022);
                veiculo3.setCor("Prata");

                // Salvar no banco de dados apenas se não existirem registros
                veiculoRepository.save(veiculo1);
                veiculoRepository.save(veiculo2);
                veiculoRepository.save(veiculo3);
            }
        };
    }
}

