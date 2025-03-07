package com.tinnova.coder.repository;

import com.tinnova.coder.domain.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByMarcaOrAnoOrCor(String marca, Integer ano, String cor);
}

