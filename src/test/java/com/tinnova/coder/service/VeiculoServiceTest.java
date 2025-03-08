package com.tinnova.coder.service;

import com.tinnova.coder.controller.dto.VeiculoDto;
import com.tinnova.coder.domain.Veiculo;
import com.tinnova.coder.repository.VeiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoServiceTest {

    @InjectMocks
    private VeiculoService veiculoService;

    @Mock
    private VeiculoRepository veiculoRepository;

    private Veiculo veiculo;
    private VeiculoDto veiculoDto;

    @BeforeEach
    void setUp() {
        veiculo = new Veiculo();
        veiculo.setId(1L);
        veiculo.setMarca("Fiat");
        veiculo.setAno(2020);
        veiculo.setCor("Preto");

        veiculoDto = new VeiculoDto(veiculo);
    }

    @Test
    void testFindAll() {
        // Arrange
        when(veiculoRepository.findAll()).thenReturn(Arrays.asList(veiculo));

        // Act
        var result = veiculoService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Fiat", result.get(0).getMarca());
    }

    @Test
    void testFindByIdFound() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        // Act
        var result = veiculoService.findById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Fiat", result.get().getMarca());
    }

    @Test
    void testFindByIdNotFound() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        var result = veiculoService.findById(1L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testUpdateVeiculoFound() {
        // Arrange
        VeiculoDto updatedVeiculoDto = new VeiculoDto();
        updatedVeiculoDto.setMarca("Fiat Updated");
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        // Act
        var result = veiculoService.updateVeiculo(1L, updatedVeiculoDto);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Fiat Updated", result.get().getMarca());
    }

    @Test
    void testUpdateVeiculoNotFound() {
        // Arrange
        VeiculoDto updatedVeiculoDto = new VeiculoDto();
        updatedVeiculoDto.setMarca("Fiat Updated");
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        var result = veiculoService.updateVeiculo(1L, updatedVeiculoDto);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testSave() {
        // Arrange
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        // Act
        var result = veiculoService.save(veiculo);

        // Assert
        assertNotNull(result);
        assertEquals("Fiat", result.getMarca());
    }

    @Test
    void testPartialUpdate() {
        // Arrange
        VeiculoDto partialUpdateDto = new VeiculoDto();
        partialUpdateDto.setMarca("Fiat Updated");
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo);

        // Act
        var result = veiculoService.partialUpdate(1L, partialUpdateDto);

        // Assert
        assertEquals("Fiat Updated", result.getMarca());
    }

    @Test
    void testDeleteFound() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        // Act
        var result = veiculoService.delete(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Fiat", result.get().getMarca());
        verify(veiculoRepository).deleteById(1L);
    }

    @Test
    void testDeleteNotFound() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        var result = veiculoService.delete(1L);

        // Assert
        assertFalse(result.isPresent());
        verify(veiculoRepository, never()).deleteById(1L);
    }

    @Test
    void testGetVeiculoByIdFound() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.of(veiculo));

        // Act
        var result = veiculoService.getVeiculoById(1L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Fiat", result.get().getMarca());
    }

    @Test
    void testGetVeiculoByIdNotFound() {
        // Arrange
        when(veiculoRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        var result = veiculoService.getVeiculoById(1L);

        // Assert
        assertFalse(result.isPresent());
    }

    @Test
    void testFindVeiculos() {
        // Arrange
        when(veiculoRepository.findByMarcaOrAnoOrCor("Fiat", 2020, "Preto"))
                .thenReturn(Arrays.asList(veiculo));

        // Act
        var result = veiculoService.findVeiculos("Fiat", 2020, "Preto");

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Fiat", result.get(0).getMarca());
    }
}
