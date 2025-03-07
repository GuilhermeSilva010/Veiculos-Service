package com.tinnova.coder.controller;

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
    private VeiculoService veiculoService;  // Supondo que você tenha um serviço que manipula a lógica de negócios

    @Operation(summary = "Recupera todos os veículos", description = "Retorna uma lista de veículos com filtros opcionais como marca, ano ou cor.")
    @GetMapping
    public List<Veiculo> getAllVeiculos(
            @RequestParam(required = false) String marca,  // Parâmetro opcional
            @RequestParam(required = false) Integer ano,   // Parâmetro opcional
            @RequestParam(required = false) String cor) { // Parâmetro opcional
        if (marca != null || ano != null || cor != null) {
            return veiculoService.findByMarcaAnoCor(marca, ano, cor);
        }
        return veiculoService.findAll();
    }

    @Operation(summary = "Recupera um veículo por ID", description = "Retorna um veículo específico pelo seu ID.")
    @GetMapping("/{id}")
    public ResponseEntity<Veiculo> getVeiculoById(@PathVariable Long id) {
        Optional<Veiculo> veiculo = veiculoService.findById(id);
        if (veiculo.isPresent()) {
            return ResponseEntity.ok(veiculo.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Cria um novo veículo", description = "Cria um novo veículo com os dados fornecidos.")
    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo novoVeiculo = veiculoService.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoVeiculo);
    }

    @Operation(summary = "Atualiza um veículo", description = "Atualiza os dados de um veículo existente. O ID do veículo é passado na URL e os dados a serem atualizados são enviados no corpo da requisição.")
    @PutMapping("/{id}")
    public ResponseEntity<Veiculo> updateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoExistente = veiculoService.findById(id);
        if (veiculoExistente.isPresent()) {
            veiculo.setId(id); // Atualiza o ID do veiculo com o valor passado na URL
            Veiculo veiculoAtualizado = veiculoService.save(veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Atualiza parcialmente um veículo", description = "Atualiza parcialmente os dados de um veículo existente. Apenas os campos fornecidos no corpo da requisição serão atualizados.")
    @PatchMapping("/{id}")
    public ResponseEntity<Veiculo> partialUpdateVeiculo(@PathVariable Long id, @RequestBody Veiculo veiculo) {
        Optional<Veiculo> veiculoExistente = veiculoService.findById(id);
        if (veiculoExistente.isPresent()) {
            Veiculo veiculoAtualizado = veiculoService.partialUpdate(id, veiculo);
            return ResponseEntity.ok(veiculoAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Deleta um veículo", description = "Deleta um veículo existente, identificado pelo ID passado na URL. Retorna uma resposta vazia com status 204 se a operação for bem-sucedida.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Long id) {
        Optional<Veiculo> veiculoExistente = veiculoService.findById(id);
        if (veiculoExistente.isPresent()) {
            veiculoService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
