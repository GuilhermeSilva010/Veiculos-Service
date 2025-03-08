package com.tinnova.coder.controller;

import com.tinnova.coder.controller.dto.VeiculoDto;
import com.tinnova.coder.domain.Veiculo;
import com.tinnova.coder.service.VeiculoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Veiculos", description = "Gerencia os veiculos da aplicaçao")
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Operation(summary = "Recupera todos os veículos", description = "Retorna uma lista de veículos com filtros opcionais como marca, ano ou cor.")
    @GetMapping
    public List<VeiculoDto> getAllVeiculos(
            @RequestParam(required = false) String marca,
            @RequestParam(required = false) Integer ano,
            @RequestParam(required = false) String cor) {
        return veiculoService.findVeiculos(marca, ano, cor);
    }

    @Operation(summary = "Recupera um veículo por ID", description = "Retorna um veículo específico pelo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDto> getVeiculoById(@PathVariable Long id) {
        Optional<VeiculoDto> veiculoExistente = veiculoService.getVeiculoById(id);

        if (veiculoExistente.isPresent()) {
            return ResponseEntity.ok(veiculoExistente.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cria um novo veículo", description = "Cria um novo veículo com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<VeiculoDto> createVeiculo(@RequestBody VeiculoDto veiculo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoService.save(new Veiculo(veiculo)));
    }

    @Operation(summary = "Atualiza um veículo", description = "Atualiza os dados de um veículo existente. O ID do veículo é passado na URL e os dados a serem atualizados são enviados no corpo da requisição.")
    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDto> updateVeiculo(@PathVariable Long id, @RequestBody VeiculoDto veiculo) {
        Optional<VeiculoDto> veiculoAtualizado = veiculoService.updateVeiculo(id, veiculo);

        if (veiculoAtualizado.isPresent()) {
            return ResponseEntity.ok(veiculoAtualizado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza parcialmente um veículo", description = "Atualiza parcialmente os dados de um veículo existente. Apenas os campos fornecidos no corpo da requisição serão atualizados.")
    @PatchMapping("/{id}")
    public ResponseEntity<VeiculoDto> partialUpdateVeiculo(@PathVariable Long id, @RequestBody VeiculoDto veiculo) {
        Optional<VeiculoDto> veiculoAtualizado = veiculoService.updateVeiculo(id, veiculo);

        if (veiculoAtualizado.isPresent()) {
            return ResponseEntity.ok(veiculoAtualizado.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta um veículo", description = "Deleta um veículo existente, identificado pelo ID passado na URL. Retorna uma resposta vazia com status 204 se a operação for bem-sucedida.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        Optional<VeiculoDto> veiculoExistente = veiculoService.delete(id);

        if (veiculoExistente.isPresent()) {
            return ResponseEntity.noContent().build();  // Sucesso na exclusão
        }

        return ResponseEntity.notFound().build();  // Veículo não encontrado
    }
}
