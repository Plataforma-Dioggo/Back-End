package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    DisciplinaResponseDTO toDTO(Disciplina disciplina);

    List<DisciplinaResponseDTO> toDTOList(List<Disciplina> disciplinas);

    Disciplina toEntity(DisciplinaRequestDTO dto);

    void updateEntityFromDTO(DisciplinaRequestDTO dto, @MappingTarget Disciplina entity);
}
