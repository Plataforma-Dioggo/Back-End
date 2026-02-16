package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.bson.types.ObjectId;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(target = "id", expression = "java(objectIdToString(disciplina.getId()))")
    DisciplinaResponseDTO toDTO(Disciplina disciplina);

    default String objectIdToString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }

    List<DisciplinaResponseDTO> toDTOList(List<Disciplina> disciplinas);

    Disciplina toEntity(DisciplinaRequestDTO dto);

    void updateEntityFromDTO(DisciplinaRequestDTO dto, @MappingTarget Disciplina entity);
}
