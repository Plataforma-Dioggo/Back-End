package com.example.plataformadioggoapi.dto;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para requisição de criação/atualização de Disciplina")
public class DisciplinaRequestDTO {

    @Schema(description = "Nome da disciplina", example = "Matemática")
    private String nome;

    @Schema(description = "ID do professor dono da disciplina", example = "65f1a9c2d3e4f56789ab0123")
    private String professorId;
}