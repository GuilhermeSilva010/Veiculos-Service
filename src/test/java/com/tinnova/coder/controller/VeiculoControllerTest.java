package com.tinnova.coder.controller;

import com.tinnova.coder.controller.dto.VeiculoDto;
import com.tinnova.coder.service.VeiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Certifique-se de que o Mockito está sendo usado corretamente
class VeiculoControllerTest {

    @InjectMocks
    private VeiculoController veiculoController;

    @Mock
    private VeiculoService veiculoService;

    private VeiculoDto veiculoDto;

    @BeforeEach
    void setUp() {
        veiculoDto = new VeiculoDto();
        veiculoDto.setId(1L);
        veiculoDto.setMarca("Fiat");
        veiculoDto.setAno(2020);
        veiculoDto.setCor("Preto");
    }

    @Test
    void testGetVeiculoByIdFound() {
        when(veiculoService.getVeiculoById(1L)).thenReturn(Optional.of(veiculoDto));

        ResponseEntity<VeiculoDto> response = veiculoController.getVeiculoById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoDto, response.getBody());
    }

    @Test
    void testGetVeiculoByIdNotFound() {
        when(veiculoService.getVeiculoById(1L)).thenReturn(Optional.empty());

        ResponseEntity<VeiculoDto> response = veiculoController.getVeiculoById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testCreateVeiculo() {
        when(veiculoService.save(any())).thenReturn(veiculoDto);

        ResponseEntity<VeiculoDto> response = veiculoController.createVeiculo(veiculoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(veiculoDto, response.getBody());
    }

    @Test
    void testUpdateVeiculoFound() {
        when(veiculoService.updateVeiculo(eq(1L), any())).thenReturn(Optional.of(veiculoDto));

        ResponseEntity<VeiculoDto> response = veiculoController.updateVeiculo(1L, veiculoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoDto, response.getBody());
    }

    @Test
    void testUpdateVeiculoNotFound() {
        when(veiculoService.updateVeiculo(eq(1L), any())).thenReturn(Optional.empty());

        ResponseEntity<VeiculoDto> response = veiculoController.updateVeiculo(1L, veiculoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testPartialUpdateVeiculoFound() {
        when(veiculoService.updateVeiculo(eq(1L), any())).thenReturn(Optional.of(veiculoDto));

        ResponseEntity<VeiculoDto> response = veiculoController.partialUpdateVeiculo(1L, veiculoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculoDto, response.getBody());
    }

    @Test
    void testPartialUpdateVeiculoNotFound() {
        when(veiculoService.updateVeiculo(eq(1L), any())).thenReturn(Optional.empty());

        ResponseEntity<VeiculoDto> response = veiculoController.partialUpdateVeiculo(1L, veiculoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteVeiculoFound() {
        when(veiculoService.delete(1L)).thenReturn(Optional.of(veiculoDto));

        ResponseEntity<Void> response = veiculoController.deleteVeiculo(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testDeleteVeiculoNotFound() {
        when(veiculoService.delete(1L)).thenReturn(Optional.empty());

        ResponseEntity<Void> response = veiculoController.deleteVeiculo(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
