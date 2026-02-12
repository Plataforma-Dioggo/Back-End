package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.mapper.DisciplinaMapper;
import com.example.plataformadioggoapi.model.Disciplina;
import com.example.plataformadioggoapi.repository.DisciplinaRepository;
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
        List<Disciplina> disciplinas = disciplinaRepository.findAll();

        return disciplinaMapper.toDTOList(disciplinas);
    }

    public DisciplinaResponseDTO buscarPorId(String id) {
        Disciplina disciplina = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        return disciplinaMapper.toDTO(disciplina);
    }

    public DisciplinaResponseDTO criarDisciplina(DisciplinaRequestDTO disciplina) {
        Disciplina novaDisciplina = disciplinaMapper.toEntity(disciplina);

        Disciplina disciplinaSalva = disciplinaRepository.save(novaDisciplina);

        return disciplinaMapper.toDTO(disciplinaSalva);
    }

    public DisciplinaResponseDTO atualizarDisciplina(String id, DisciplinaRequestDTO disciplina) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        disciplinaMapper.updateEntityFromDTO(disciplina, disciplinaExistente);

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return disciplinaMapper.toDTO(salva);
    }

    public DisciplinaResponseDTO atualizarParcialDisciplina(String id, DisciplinaRequestDTO disciplina) {
        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        if (disciplina.getNome() != null && !disciplina.getNome().isBlank()) {
            disciplinaExistente.setNome(disciplina.getNome());
        }

        if (disciplina.getUsuarioProfessor() != null && !disciplina.getUsuarioProfessor().isBlank()) {
            disciplinaExistente.setUsuarioProfessor(disciplina.getUsuarioProfessor());
        }

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return disciplinaMapper.toDTO(salva);
    }

    public void deletarDisciplina(String id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina de ID " + id + " não encontrada.");
        }
        disciplinaRepository.deleteById(id);
    }
}
