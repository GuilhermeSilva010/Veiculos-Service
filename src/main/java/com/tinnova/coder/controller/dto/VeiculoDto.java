package com.tinnova.coder.controller.dto;

import com.tinnova.coder.domain.Veiculo;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
public class VeiculoDto {
    private Long id;
    private String marca;
    private Integer ano;
    private String cor;

    public VeiculoDto() {}

    public VeiculoDto(Veiculo veiculo) {
        this.id = veiculo.getId();
        this.marca = veiculo.getMarca();
        this.ano = veiculo.getAno();
        this.cor = veiculo.getCor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VeiculoDto that = (VeiculoDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(marca, that.marca) &&
                Objects.equals(ano, that.ano) &&
                Objects.equals(cor, that.cor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, marca, ano, cor);
    }
}
