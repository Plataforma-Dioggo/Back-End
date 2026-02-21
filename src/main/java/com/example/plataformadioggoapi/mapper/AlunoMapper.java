package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.AlunoRequestDTO;
import com.example.plataformadioggoapi.dto.AlunoResponseDTO;
import com.example.plataformadioggoapi.model.Aluno;

public class AlunoMapper {
    public static Aluno toEntity(AlunoRequestDTO alunoRequestDTO) {
        return Aluno.builder()
                .matricula(alunoRequestDTO.getMatricula())
                .nome(alunoRequestDTO.getNome())
                .email(alunoRequestDTO.getEmail())
                .senha(alunoRequestDTO.getSenha())
                .build();
    }

    public static AlunoResponseDTO toResponseDTO(Aluno aluno) {
        return AlunoResponseDTO.builder()
                .id(aluno.getId())
                .matricula(aluno.getMatricula())
                .nome(aluno.getNome())
                .email(aluno.getEmail())
                .build();
    }
}
