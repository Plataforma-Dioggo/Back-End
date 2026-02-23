package com.example.plataformadioggoapi.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AlunoResponseDTO {
    private String id;
    private String nome;
    private String matricula;
    private String email;
}
