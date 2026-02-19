package com.example.plataformadioggoapi.mapper;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.model.Disciplina;
import org.bson.types.ObjectId;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DisciplinaMapper {

    @Mapping(target = "professorId", source = "professorId", qualifiedByName = "objectIdToString")
    DisciplinaResponseDTO toDTO(Disciplina disciplina);

    List<DisciplinaResponseDTO> toDTOList(List<Disciplina> disciplinas);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professorId", source = "professorId", qualifiedByName = "stringToObjectId")
    Disciplina toEntity(DisciplinaRequestDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "professorId", source = "professorId", qualifiedByName = "stringToObjectId")
    void updateEntityFromDTO(DisciplinaRequestDTO dto, @MappingTarget Disciplina entity);

    @Named("objectIdToString")
    default String objectIdToString(ObjectId id) {
        return id != null ? id.toHexString() : null;
    }

    @Named("stringToObjectId")
    default ObjectId stringToObjectId(String id) {
        if (id == null || id.isBlank()) return null;
        return new ObjectId(id);
    }
}
