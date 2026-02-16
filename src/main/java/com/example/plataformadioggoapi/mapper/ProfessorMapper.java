package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.model.Professor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfessorMapper {

    ProfessorResponseDTO toDTO(Professor professor);

    List<ProfessorResponseDTO> toDTOList(List<Professor> professores);

    Professor toEntity(ProfessorRequestDTO dto);

    void updateEntityFromDTO(ProfessorRequestDTO dto, @MappingTarget Professor entity);
}
