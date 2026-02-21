package com.example.plataformadioggoapi.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ObservacaoResponseDTO {
    private String professor_id;
    private String titulo;
    private String descricao;
    private LocalDate data;
}
