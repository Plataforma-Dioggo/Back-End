package com.example.plataformadioggoapi.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "DTO para requisição de criação/atualização de Professor")
public class ProfessorRequestDTO {

    @Schema(description = "Nome do professor", example = "João Silva")
    private String nome;

    @Schema(description = "Usuário do professor", example = "Usuario")
    private String usuario;

    @Schema(description = "Senha do professor", example = "Senha123")
    private String senha;

    @Schema(
            description = "IDs das disciplinas associadas ao professor",
            example = "[\"65f1a9c2d3e4f56789ab0123\"]"
    )
    private List<String> disciplinaId;
}
