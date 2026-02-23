package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.TurmaRequestDTO;
import com.example.plataformadioggoapi.dto.TurmaResponseDTO;
import com.example.plataformadioggoapi.model.Turma;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TurmaMapper {

    TurmaResponseDTO toTurmaResponseDTO(Turma turma);

    Turma toTurma(TurmaRequestDTO turmaRequestDTO);
}
