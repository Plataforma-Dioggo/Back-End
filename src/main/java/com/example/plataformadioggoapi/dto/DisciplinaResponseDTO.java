package com.example.plataformadioggoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

    @Schema(description = "ID do professor", example = "65f1a9c2d3e4f56789ab0123")
    private String professorId;

    @Schema(description = "Nome do professor", example = "João Silva")
    private String professorNome;

    @Schema(description = "Usuário do professor", example = "joao.silva")
    private String professorUsuario;
}
