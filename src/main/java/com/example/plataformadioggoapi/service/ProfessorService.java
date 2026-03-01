package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.exception.BadRequestException;
import com.example.plataformadioggoapi.exception.EntityNotFoundException;
import com.example.plataformadioggoapi.mapper.ProfessorMapper;
import com.example.plataformadioggoapi.model.Professor;
import com.example.plataformadioggoapi.repository.ProfessorRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;
    private final PasswordEncoder passwordEncoder;

    public ProfessorService(
            ProfessorRepository professorRepository,
            ProfessorMapper professorMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<ProfessorResponseDTO> listarProfessores() {
        List<Professor> professores = professorRepository.findAll();
        return professorMapper.toDTOList(professores);
    }

    public ProfessorResponseDTO buscarPorId(String id) {

        Professor professor = professorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Professor não encontrado"));

        return professorMapper.toDTO(professor);
    }

    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO dto) {

        if (dto.getNome() == null || dto.getNome().isBlank()) {
            throw new BadRequestException("O nome é obrigatório.");
        }

        if (dto.getUsuario() == null || dto.getUsuario().isBlank()) {
            throw new BadRequestException("O usuário é obrigatório.");
        }

        if (dto.getSenha() == null || dto.getSenha().isBlank()) {
            throw new BadRequestException("A senha é obrigatória.");
        }

        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        Professor novo = professorMapper.toEntity(dto);

        Professor salvo = professorRepository.save(novo);

        return professorMapper.toDTO(salvo);
    }

    public ProfessorResponseDTO atualizarProfessor(String id, ProfessorRequestDTO dto) {

        Professor existente = professorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Professor de ID " + id + " não encontrado."));

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            dto.setSenha(passwordEncoder.encode(dto.getSenha()));
        } else {
            dto.setSenha(existente.getSenha());
        }

        professorMapper.updateEntityFromDTO(dto, existente);

        Professor salvo = professorRepository.save(existente);

        return buscarPorId(salvo.getId());
    }

    public ProfessorResponseDTO atualizarParcialProfessor(String id, ProfessorRequestDTO dto) {

        Professor existente = professorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Professor de ID " + id + " não encontrado."));

        if (dto.getNome() != null && !dto.getNome().isBlank()) {
            existente.setNome(dto.getNome());
        }

        if (dto.getUsuario() != null && !dto.getUsuario().isBlank()) {
            existente.setUsuario(dto.getUsuario());
        }

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        if (dto.getDisciplinaId() != null) {
            existente.setDisciplinaId(dto.getDisciplinaId());
        }

        Professor salvo = professorRepository.save(existente);

        return buscarPorId(salvo.getId());
    }

    public void deletarProfessor(String id) {

        if (!professorRepository.existsById(id)) {
            throw new EntityNotFoundException("Professor de ID " + id + " não encontrado.");
        }

        professorRepository.deleteById(id);
    }
}