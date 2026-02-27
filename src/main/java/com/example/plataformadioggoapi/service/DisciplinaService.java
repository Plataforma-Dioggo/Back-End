package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.DisciplinaRequestDTO;
import com.example.plataformadioggoapi.dto.DisciplinaResponseDTO;
import com.example.plataformadioggoapi.mapper.DisciplinaMapper;
import com.example.plataformadioggoapi.model.Disciplina;
import com.example.plataformadioggoapi.model.Turma;
import com.example.plataformadioggoapi.repository.DisciplinaRepository;
import org.bson.types.ObjectId;
import com.example.plataformadioggoapi.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;
    private final TurmaRepository turmaRepository;

    public DisciplinaService(
            DisciplinaRepository repository,
            DisciplinaMapper disciplinaMapper,
            TurmaRepository turmaRepository
    ) {
        this.disciplinaRepository = repository;
        this.disciplinaMapper = disciplinaMapper;
        this.turmaRepository = turmaRepository;
    }

    public List<DisciplinaResponseDTO> listarDisciplinas() {
        return disciplinaRepository.listarDisciplinasComProfessor();
    }

    public DisciplinaResponseDTO buscarPorId(String id) {
        return disciplinaRepository.buscarDisciplinaComProfessor(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));
    }

    public List<DisciplinaResponseDTO> buscarPorProfessorOuTurma(String professorId, String turmaId) {
        if (professorId != null) {
            return disciplinaRepository.findByProfessorId(professorId);
        }

        if (turmaId != null) {
            Turma turma = turmaRepository.findById(turmaId)
                    .orElseThrow(() -> new RuntimeException("Turma não encontrada"));;

            List<String> discipinaIds = turma.getDisciplinaId();

            List<Disciplina> disciplinas = disciplinaRepository.findAllById(discipinaIds);

            return disciplinaMapper.toDTOList(disciplinas);
        }

        throw new RuntimeException("Informe professorId ou turmaId");
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

        return buscarPorId(salva.getId());
    }

    public DisciplinaResponseDTO atualizarParcialDisciplina(String id, DisciplinaRequestDTO disciplina) {

        Disciplina disciplinaExistente = disciplinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Disciplina de ID " + id + " não encontrada."));

        if (disciplina.getNome() != null && !disciplina.getNome().isBlank()) {
            disciplinaExistente.setNome(disciplina.getNome());
        }

        Disciplina salva = disciplinaRepository.save(disciplinaExistente);

        return buscarPorId(salva.getId());
    }

    public void deletarDisciplina(String id) {
        if (!disciplinaRepository.existsById(id)) {
            throw new RuntimeException("Disciplina de ID " + id + " não encontrada.");
        }
        disciplinaRepository.deleteById(id);
    }

    public List<String> retornarIdDiciplina (String idProfessor){
        ObjectId professorObjectId = new ObjectId(idProfessor);
        List<Disciplina> response = disciplinaRepository.findDisciplinaByProfessorId(professorObjectId);
        List<String> listResponse = new ArrayList<>();
        for (Disciplina disciplina : response){
            listResponse.add(disciplina.getId());
        }
        return listResponse;
    }
}
