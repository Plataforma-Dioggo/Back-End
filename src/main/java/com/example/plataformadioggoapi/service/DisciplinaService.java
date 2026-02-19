package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.mapper.DisciplinaMapper;
import com.example.plataformadioggoapi.model.Disciplina;
import com.example.plataformadioggoapi.repository.DisciplinaRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;

    public DisciplinaService(DisciplinaRepository repository, DisciplinaMapper disciplinaMapper) {
        this.disciplinaRepository = repository;
        this.disciplinaMapper = disciplinaMapper;
    }

    public List<DisciplinaResponseDTO> listarDisciplinas() {
        return disciplinaRepository.findAll()
                .stream()
                .map(disciplinaMapper::toDTO)
                .toList();
    }

    public DisciplinaResponseDTO buscarPorId(ObjectId id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        return disciplinaMapper.toDTO(disciplina);
    }

    public DisciplinaResponseDTO criarDisciplina(DisciplinaRequestDTO dto) {

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RuntimeException("O nome da disciplina é obrigatório.");
        }

        if (dto.getProfessorId() == null || dto.getProfessorId().isBlank()) {
            throw new RuntimeException("O professorId é obrigatório.");
        }

        if (!ObjectId.isValid(dto.getProfessorId())) {
            throw new RuntimeException("professorId inválido.");
        }

        Disciplina novaDisciplina = disciplinaMapper.toEntity(dto);

        Disciplina disciplinaSalva = disciplinaRepository.save(novaDisciplina);

        return disciplinaMapper.toDTO(disciplinaSalva);
    }

    public DisciplinaResponseDTO atualizarDisciplina(ObjectId id, DisciplinaRequestDTO dto) {

        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new RuntimeException("O nome da disciplina é obrigatório.");
        }

        if (dto.getProfessorId() == null || dto.getProfessorId().isBlank()) {
            throw new RuntimeException("O professorId é obrigatório.");
        }

        if (!ObjectId.isValid(dto.getProfessorId())) {
            throw new RuntimeException("professorId inválido.");
        }

        disciplinaMapper.updateEntityFromDTO(dto, disciplinaExistente);

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return disciplinaMapper.toDTO(salva);
    }

    public DisciplinaResponseDTO atualizarParcialDisciplina(ObjectId id, DisciplinaRequestDTO dto) {

        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            disciplinaExistente.setNome(dto.getNome());
        }

        if (dto.getProfessorId() != null && !dto.getProfessorId().isBlank()) {

            if (!ObjectId.isValid(dto.getProfessorId())) {
                throw new RuntimeException("professorId inválido.");
            }

            disciplinaExistente.setProfessorId(new ObjectId(dto.getProfessorId()));
        }

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return disciplinaMapper.toDTO(salva);
    }

    public void deletarDisciplina(ObjectId id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina de ID " + id + " não encontrada.");
        }
        disciplinaRepository.deleteById(id);
    }
}
