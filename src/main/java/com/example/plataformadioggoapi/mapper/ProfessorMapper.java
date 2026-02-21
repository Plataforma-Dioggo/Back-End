package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Professor;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    @Mapping(target = "nomeDisciplinas", ignore = true)
    ProfessorResponseDTO toDTO(Professor professor);

    List<ProfessorResponseDTO> toDTOList(List<Professor> professores);

    @Mapping(target = "id", ignore = true)
    Professor toEntity(ProfessorRequestDTO dto);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(ProfessorRequestDTO dto, @MappingTarget Professor entity);
}