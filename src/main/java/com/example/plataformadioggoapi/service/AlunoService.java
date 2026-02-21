package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.AlunoRequestDTO;
import com.example.plataformadioggoapi.dto.ObservacaoRequestDTO;
import com.example.plataformadioggoapi.dto.ObservacaoResponseDTO;
import com.example.plataformadioggoapi.mapper.AlunoMapper;
import com.example.plataformadioggoapi.mapper.ObservacaoMapper;
import com.example.plataformadioggoapi.model.Aluno;
import com.example.plataformadioggoapi.model.Observacao;
import com.example.plataformadioggoapi.repository.AlunoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository alunoRepository,
                        PasswordEncoder passwordEncoder) {
        this.alunoRepository = alunoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Aluno cadastrarAluno(AlunoRequestDTO aluno) {

        Aluno entity = AlunoMapper.toEntity(aluno);

        entity.setSenha(passwordEncoder.encode(aluno.getSenha()));

        return alunoRepository.save(entity);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
    }

    public void adicionarObservacao(String matricula,
                                    ObservacaoRequestDTO request) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new RuntimeException("Aluno não encontrado"));

        Observacao observacao = ObservacaoMapper
                .toEntity(request, "PROFESSOR_ID_AQUI");

        if (aluno.getObservacoes() == null) {
            aluno.setObservacoes(new ArrayList<>());
        }

        aluno.getObservacoes().add(observacao);

        alunoRepository.save(aluno);
    }


    public List<ObservacaoResponseDTO> listarObservacoes(String matricula) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new RuntimeException("Aluno não encontrado"));

        if (aluno.getObservacoes() == null) {
            return new ArrayList<>();
        }

        return aluno.getObservacoes()
                .stream()
                .map(ObservacaoMapper::toResponseDTO)
                .toList();
    }
}
