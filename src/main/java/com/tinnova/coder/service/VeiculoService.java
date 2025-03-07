package com.tinnova.coder.service;

import com.tinnova.coder.domain.Veiculo;
import com.tinnova.coder.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    public VeiculoService(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> findById(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo partialUpdate(Long id, Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(id).orElseThrow();
        // Atualiza parcialmente os campos
        if (veiculo.getMarca() != null) veiculoExistente.setMarca(veiculo.getMarca());
        if (veiculo.getAno() != null) veiculoExistente.setAno(veiculo.getAno());
        if (veiculo.getCor() != null) veiculoExistente.setCor(veiculo.getCor());
        return veiculoRepository.save(veiculoExistente);
    }

    public void delete(Long id) {
        veiculoRepository.deleteById(id);
    }

    public List<Veiculo> findByMarcaAnoCor(String marca, Integer ano, String cor) {
        return veiculoRepository.findByMarcaOrAnoOrCor(marca, ano, cor);
    }
}

