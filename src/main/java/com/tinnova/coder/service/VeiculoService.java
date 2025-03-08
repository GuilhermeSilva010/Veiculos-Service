package com.tinnova.coder.service;

import com.tinnova.coder.controller.dto.VeiculoDto;
import com.tinnova.coder.domain.Veiculo;
import com.tinnova.coder.repository.VeiculoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<VeiculoDto> updateVeiculo(Long id, VeiculoDto veiculoDto) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);

        if (veiculoExistente.isPresent()) {
            // Recupera o veículo existente
            Veiculo veiculoAtual = veiculoExistente.get();

            // Atualiza os campos do veículo com os dados do DTO
            if (veiculoDto.getMarca() != null) veiculoAtual.setMarca(veiculoDto.getMarca());
            if (veiculoDto.getAno() != null) veiculoAtual.setAno(veiculoDto.getAno());
            if (veiculoDto.getCor() != null) veiculoAtual.setCor(veiculoDto.getCor());

            // Define o ID com o valor passado na URL
            veiculoAtual.setId(id);

            // Salva o veículo atualizado
            Veiculo veiculoAtualizado = veiculoRepository.save(veiculoAtual);

            // Retorna o veículo atualizado como DTO
            return Optional.of(new VeiculoDto(veiculoAtualizado));
        }
        return Optional.empty();  // Retorna Optional vazio caso o veículo não seja encontrado
    }


    public VeiculoDto save(Veiculo veiculo) {
        return new VeiculoDto(veiculoRepository.save(veiculo));
    }

    public VeiculoDto partialUpdate(Long id, VeiculoDto veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(id).orElseThrow(() -> new IllegalStateException("Veículo não encontrado"));
        // Atualiza parcialmente os campos
        if (veiculo.getMarca() != null) veiculoExistente.setMarca(veiculo.getMarca());
        if (veiculo.getAno() != null) veiculoExistente.setAno(veiculo.getAno());
        if (veiculo.getCor() != null) veiculoExistente.setCor(veiculo.getCor());
        return new VeiculoDto(veiculoRepository.save(veiculoExistente));
    }

    public Optional<VeiculoDto> delete(Long id) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);

        // Se o veículo existir, exclui ele
        if (veiculoExistente.isPresent()) {
            veiculoRepository.deleteById(id);
            return Optional.of(new VeiculoDto(veiculoExistente.get()));  // Retorna o Veículo como DTO
        }

        // Caso o veículo não exista, retorna Optional vazio
        return Optional.empty();
    }

    public Optional<VeiculoDto> getVeiculoById(Long id) {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);

        // Se o veículo existir, exclui ele
        if (veiculoExistente.isPresent()) {
            return Optional.of(new VeiculoDto(veiculoExistente.get()));
        }

        return Optional.empty();
    }


    public List<VeiculoDto> findVeiculos(String marca, Integer ano, String cor) {
        // Verifica se qualquer um dos parâmetros é diferente de null
        if (marca != null || ano != null || cor != null) {
            return findByMarcaAnoCor(marca, ano, cor);
        }
        // Se todos os parâmetros forem null, retorna todos os veículos
        return findAll()
                .stream()
                .map(VeiculoDto::new)
                .collect(Collectors.toList());
    }

    public List<VeiculoDto> findByMarcaAnoCor(String marca, Integer ano, String cor) {
        return veiculoRepository.findByMarcaOrAnoOrCor(marca, ano, cor)
                .stream()
                .map(VeiculoDto::new)  // Converte cada Veiculo para VeiculoDto
                .collect(Collectors.toList());
    }
}

