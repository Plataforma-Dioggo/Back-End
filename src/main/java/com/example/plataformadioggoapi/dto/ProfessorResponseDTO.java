package com.example.plataformadioggoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.bson.types.ObjectId;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para resposta de Professor")
public class ProfessorResponseDTO {

    @Schema(description = "Nome do professor", example = "João Pedro")
    private String nome;

    @Schema(description = "Usuário do professor", example = "Usuario")
    private String usuario;

    @Schema(description = "Disciplinas associadas ao professor", example = "[Matemática, Física]")
    private List<   String> nomeDisciplinas;
}
