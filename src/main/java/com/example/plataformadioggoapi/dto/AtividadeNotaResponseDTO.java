package com.example.plataformadioggoapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AtividadeNotaResponseDTO {
    private Double nota;
    private String nomeAtividade;
    private String atribuicao;
}
