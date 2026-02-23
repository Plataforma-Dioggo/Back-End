package com.example.plataformadioggoapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NotasResponseDTO {
    private List<AtividadeNotaResponseDTO> atividades;
    private List<Double> notaN1;
    private List<Double> notaN2;
    private Double mediaN1;
    private Double mediaN2;
    private Double mediaFinal;
}
