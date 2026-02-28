package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.TurmaRequestDTO;
import com.example.plataformadioggoapi.dto.TurmaResponseDTO;
import com.example.plataformadioggoapi.mapper.TurmaMapper;
import com.example.plataformadioggoapi.model.AlunoTurma;
import com.example.plataformadioggoapi.model.Turma;
import com.example.plataformadioggoapi.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;

    private final DisciplinaService disciplinaService;

    private final TurmaMapper turmaMapper;

    public TurmaService(TurmaRepository turmaRepository, DisciplinaService disciplinaService, TurmaMapper turmaMapper) {
        this.turmaRepository = turmaRepository;
        this.disciplinaService = disciplinaService;
        this.turmaMapper = turmaMapper;
    }

    public List<TurmaResponseDTO> listarTurmas() {
        return turmaRepository.findAll().stream()
                .map(turmaMapper::toTurmaResponseDTO)
                .collect(Collectors.toList());
    }

    public Boolean adicionarAluno(String nomeTurma, String nomeAluno, String matriculaAluno) {
        Turma turma = ListarTurmasPorNome(nomeTurma);
        if (turma.getAlunos() == null) {
            return false;
        }
        AlunoTurma aluno = new AlunoTurma(nomeAluno,matriculaAluno);
        turma.getAlunos().add(aluno);
        turmaRepository.save(turma);
        return true;

    }

    public Boolean removerAluno(String nomeTurma, String nomeAluno, String matriculaAluno){
        Turma turma = ListarTurmasPorNome(nomeTurma);
        AlunoTurma alunoParaRemover = null;
        for (AlunoTurma alunoTurma : turma.getAlunos()){
            if (alunoTurma.getNome().equals(nomeAluno) && alunoTurma.getMatricula().equals(matriculaAluno)){
                alunoParaRemover = alunoTurma;
                break;
            }
        }

        if (alunoParaRemover != null) {
            turma.getAlunos().remove(alunoParaRemover);
            turmaRepository.save(turma);
            return true;
        }
        return false;
    }

    public Boolean criarTurma(TurmaRequestDTO turmaRequestDTO){
        Turma turma = turmaMapper.toTurma(turmaRequestDTO);
        turma.setLiberarNotas(false);
        turmaRepository.save(turma);
        return true;
    }

    public Boolean apagarTurma(String id) {
        if (turmaRepository.existsById(id)) {
            turmaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Turma ListarTurmasPorNome(String nomeTurma) {
        Turma turma = turmaRepository.findByNome(nomeTurma);
        if (turma == null) {
            throw new RuntimeException("Turma não encontrada: " + nomeTurma);
        }
        return turma;
    }

    public List<TurmaResponseDTO> ListarIdprofessor (String idProfessor){
        List<String> disciplinasIds = disciplinaService.retornarIdDiciplina(idProfessor);
        List<TurmaResponseDTO> list = listarTurmas();
        List<TurmaResponseDTO> response = new ArrayList<>();
        for (TurmaResponseDTO turma: list){
            if (turma.getDisciplinaId() != null) {
                for (String disciplinaIdTurma : turma.getDisciplinaId()) {
                    if (disciplinasIds.contains(disciplinaIdTurma)) {
                        response.add(turma);
                        break;
                    }
                }
            }
        }
        return response;
    }

    public Boolean alternarLiberacaoNotas(String nomeTurma) {
            Turma turma = ListarTurmasPorNome(nomeTurma);

        if (turma == null) {
            throw new RuntimeException("Turma não encontrada: " + nomeTurma);
        }

        turma.setLiberarNotas(!turma.getLiberarNotas());

        if (turma.getId() == null || turma.getId().isEmpty()) {
            throw new RuntimeException("ID da turma não pode ser nulo");
        }

        Turma turmaSalva = turmaRepository.save(turma);

        return turmaSalva.getLiberarNotas();
    }

}
