package com.example.plataformadioggoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para resposta de Professor")
public class ProfessorResponseDTO {

    @Schema(description = "ID do professor", example = "65f1a9c2d3e4f56789ab0123")
    private String id;

    @Schema(description = "Nome do professor", example = "João Pedro")
    private String nome;

    @Schema(description = "Usuário do professor", example = "Usuario")
    private String usuario;

    @Schema(description = "IDs das disciplinas associadas", example = "[\"65f1a9c2d3e4f56789ab0123\"]")
    private List<String> disciplinaId;

    @Schema(description = "Nomes das disciplinas associadas", example = "[\"Matemática\", \"Física\"]")
    private List<String> nomeDisciplinas;
}
