package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.*;
import com.example.plataformadioggoapi.exception.BadRequestException;
import com.example.plataformadioggoapi.exception.EntityNotFoundException;
import com.example.plataformadioggoapi.mapper.DisciplinaMapper;
import com.example.plataformadioggoapi.mapper.DisciplinaProfessorMapper;
import com.example.plataformadioggoapi.model.Disciplina;
import com.example.plataformadioggoapi.model.Turma;
import com.example.plataformadioggoapi.repository.DisciplinaRepository;
import com.example.plataformadioggoapi.repository.TurmaRepository;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final DisciplinaProfessorMapper disciplinaProfessorMapper;
    private final TurmaRepository turmaRepository;

    public DisciplinaService(
            DisciplinaRepository repository,
            DisciplinaMapper disciplinaMapper,
            DisciplinaProfessorMapper disciplinaProfessorMapper,
            TurmaRepository turmaRepository
    ) {
        this.disciplinaRepository = repository;
        this.disciplinaMapper = disciplinaMapper;
        this.disciplinaProfessorMapper = disciplinaProfessorMapper;
        this.turmaRepository = turmaRepository;
    }

    public List<DisciplinaProfessorResponseDTO> listarDisciplinas() {
        return disciplinaRepository.listarDisciplinasComProfessor();
    }

    public DisciplinaProfessorResponseDTO buscarPorId(String id) {

        ObjectId objectId = new ObjectId(id);

        return disciplinaRepository.buscarDisciplinaComProfessor(objectId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Disciplina de ID " + id + " não encontrada."));
    }

    public List<DisciplinaProfessorResponseDTO> buscarPorProfessorOuTurma(String professorId, String turmaId) {

        if (professorId != null) {
            return disciplinaRepository.findByProfessorId(professorId);
        }

        if (turmaId != null) {
            Turma turma = turmaRepository.findById(turmaId)
                    .orElseThrow(() ->
                            new EntityNotFoundException("Turma não encontrada."));

            List<String> disciplinaIds = turma.getDisciplinaId();
            List<Disciplina> disciplinas = disciplinaRepository.findAllById(disciplinaIds);

            return disciplinaProfessorMapper.toDTOList(disciplinas);
        }

        throw new BadRequestException("Informe professorId ou turmaId.");
    }

    public DisciplinaProfessorResponseDTO criarDisciplina(DisciplinaRequestDTO disciplina) {

        Disciplina novaDisciplina = disciplinaMapper.toEntity(disciplina);
        Disciplina disciplinaSalva = disciplinaRepository.save(novaDisciplina);

        DisciplinaProfessorResponseDTO disciplinaProf = buscarPorId(disciplinaSalva.getId());

        return disciplinaProf;
    }

    public DisciplinaResponseDTO atualizarDisciplina(String id, DisciplinaRequestDTO disciplina) {

        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Disciplina de ID " + id + " não encontrada."));

        disciplinaMapper.updateEntityFromDTO(disciplina, disciplinaExistente);

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return disciplinaMapper.toDTO(salva);
    }

    public DisciplinaResponseDTO atualizarParcialDisciplina(String id, DisciplinaRequestDTO disciplina) {

        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Disciplina de ID " + id + " não encontrada."));

        if (disciplina.getNome() != null && !disciplina.getNome().isBlank()) {
            disciplinaExistente.setNome(disciplina.getNome());
        }

        if (disciplina.getProfessorId() != null && !disciplina.getProfessorId().isBlank()) {
            disciplinaExistente.setProfessorId(disciplina.getProfessorId());
        }

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return disciplinaMapper.toDTO(salva);
    }

    public void deletarDisciplina(String id) {

        if (!disciplinaRepository.existsById(id)) {
            throw new EntityNotFoundException("Disciplina de ID " + id + " não encontrada.");
        }

        disciplinaRepository.deleteById(id);
    }

    public List<String> retornarIdDisciplina(String idProfessor) {

        List<Disciplina> response =
                disciplinaRepository.findDisciplinaByProfessorId(idProfessor);

        if (response.isEmpty()) {
            throw new EntityNotFoundException("Nenhuma disciplina encontrada para o professor informado.");
        }

        List<String> listResponse = new ArrayList<>();

        for (Disciplina disciplina : response) {
            listResponse.add(disciplina.getId());
        }

        return listResponse;
    }
}