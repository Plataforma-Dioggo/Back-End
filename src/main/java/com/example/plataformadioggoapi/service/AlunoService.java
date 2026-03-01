package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.AlunoRequestDTO;
import com.example.plataformadioggoapi.dto.AlunoResponseDTO;
import com.example.plataformadioggoapi.dto.ObservacaoRequestDTO;
import com.example.plataformadioggoapi.dto.ObservacaoResponseDTO;
import com.example.plataformadioggoapi.exception.EntityNotFoundException;
import com.example.plataformadioggoapi.mapper.AlunoMapper;
import com.example.plataformadioggoapi.mapper.ObservacaoMapper;
import com.example.plataformadioggoapi.model.Aluno;
import com.example.plataformadioggoapi.model.Observacao;
import com.example.plataformadioggoapi.repository.AlunoRepository;
import com.example.plataformadioggoapi.repository.ProfessorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final ProfessorRepository professorRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository alunoRepository,
                        ProfessorRepository professorRepository,
                        PasswordEncoder passwordEncoder) {
        this.alunoRepository = alunoRepository;
        this.professorRepository = professorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private AlunoResponseDTO montarAlunoResponseCompleto(Aluno aluno) {

        List<ObservacaoResponseDTO> observacoesDTO = new ArrayList<>();

        if (aluno.getObservacoes() != null) {
            observacoesDTO = aluno.getObservacoes()
                    .stream()
                    .map(observacao -> {

                        var professor = professorRepository
                                .findById(observacao.getProfessorId())
                                .orElse(null);

                        String nome = professor != null ? professor.getNome() : null;
                        String usuario = professor != null ? professor.getUsuario() : null;

                        return ObservacaoMapper.toResponseDTO(
                                observacao,
                                nome,
                                usuario
                        );
                    })
                    .toList();
        }

        return AlunoResponseDTO.builder()
                .id(aluno.getId())
                .nome(aluno.getNome())
                .matricula(aluno.getMatricula())
                .email(aluno.getEmail())
                .usouSistema(aluno.getUsouSistema())
                .turmaId(aluno.getTurmaId())
                .observacoes(observacoesDTO)
                .build();
    }

    public List<AlunoResponseDTO> listarAlunos() {
        return alunoRepository.findAll()
                .stream()
                .map(this::montarAlunoResponseCompleto)
                .toList();
    }

    public AlunoResponseDTO cadastrarAluno(AlunoRequestDTO alunoDTO) {

        Aluno entity = AlunoMapper.toEntity(alunoDTO);
        entity.setSenha(passwordEncoder.encode(alunoDTO.getSenha()));

        Aluno salvo = alunoRepository.save(entity);

        return montarAlunoResponseCompleto(salvo);
    }

    public AlunoResponseDTO buscarPorMatricula(String matricula) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        return montarAlunoResponseCompleto(aluno);
    }

    public AlunoResponseDTO editarAluno(String matricula, AlunoRequestDTO alunoDTO) {

        Aluno alunoEditado = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        if (alunoDTO.getNome() != null) {
            alunoEditado.setNome(alunoDTO.getNome());
        }
        if (alunoDTO.getEmail() != null) {
            alunoEditado.setEmail(alunoDTO.getEmail());
        }
        if (alunoDTO.getSenha() != null) {
            alunoEditado.setSenha(passwordEncoder.encode(alunoDTO.getSenha()));
        }
        if (alunoDTO.getTurmaId() != null) {
            alunoEditado.setTurmaId(alunoDTO.getTurmaId());
        }

        Aluno salvo = alunoRepository.save(alunoEditado);

        return montarAlunoResponseCompleto(salvo);
    }

    public AlunoResponseDTO excluirAluno(String matricula) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        alunoRepository.delete(aluno);

        return montarAlunoResponseCompleto(aluno);
    }

    public void adicionarObservacao(String matricula, ObservacaoRequestDTO request) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        Observacao observacao = ObservacaoMapper.toEntity(request);

        if (aluno.getObservacoes() == null) {
            aluno.setObservacoes(new ArrayList<>());
        }

        aluno.getObservacoes().add(observacao);
        alunoRepository.save(aluno);
    }

    public List<ObservacaoResponseDTO> listarObservacoes(String matricula) {
        return buscarPorMatricula(matricula).getObservacoes();
    }
}