package com.example.plataformadioggoapi.dto;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para resposta de Disciplina")
public class DisciplinaResponseDTO {

    @Schema(description = "ID da disciplina", example = "65f1a9c2d3e4f56789ab0123")
    private String id;

    @Schema(description = "Nome da disciplina", example = "Matemática")
    private String nome;
}