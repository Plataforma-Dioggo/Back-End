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

    @Schema(description = "ID único da disciplina", example = "507f1f77bcf86cd799439011")
    private String id;

    @Schema(description = "Nome da disciplina", example = "Matemática")
    private String nome;

}
