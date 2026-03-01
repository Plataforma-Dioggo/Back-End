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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final PasswordEncoder passwordEncoder;

    public AlunoService(AlunoRepository alunoRepository, PasswordEncoder passwordEncoder) {
        this.alunoRepository = alunoRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AlunoResponseDTO> listarAlunos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream().map(AlunoMapper::toResponseDTO).toList();
    }

    public Aluno cadastrarAluno(AlunoRequestDTO aluno) {
        Aluno entity = AlunoMapper.toEntity(aluno);
        entity.setSenha(passwordEncoder.encode(aluno.getSenha()));
        return alunoRepository.save(entity);
    }

    public Optional<Aluno> buscarPorMatricula(String matricula) {
        return alunoRepository.findByMatricula(matricula);
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

        Aluno alunoSalvo = alunoRepository.save(alunoEditado);
        return AlunoMapper.toResponseDTO(alunoSalvo);
    }

    public AlunoResponseDTO excluirAluno(String matricula) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        alunoRepository.delete(aluno);
        return AlunoMapper.toResponseDTO(aluno);
    }

    public void adicionarObservacao(String matricula, ObservacaoRequestDTO request) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        Observacao observacao =
                ObservacaoMapper.toEntity(request);

        if (aluno.getObservacoes() == null) {
            aluno.setObservacoes(new ArrayList<>());
        }

        aluno.getObservacoes().add(observacao);
        alunoRepository.save(aluno);
    }

    public List<ObservacaoResponseDTO> listarObservacoes(String matricula) {

        Aluno aluno = alunoRepository.findByMatricula(matricula)
                .orElseThrow(() ->
                        new EntityNotFoundException("Aluno não encontrado"));

        if (aluno.getObservacoes() == null) {
            return new ArrayList<>();
        }

        return aluno.getObservacoes()
                .stream()
                .map(ObservacaoMapper::toResponseDTO)
                .toList();
    }
}