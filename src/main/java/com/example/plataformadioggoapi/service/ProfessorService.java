package com.example.plataformadioggoapi.service;

import com.example.plataformadioggoapi.dto.ProfessorRequestDTO;
import com.example.plataformadioggoapi.dto.ProfessorResponseDTO;
import com.example.plataformadioggoapi.mapper.ProfessorMapper;
import com.example.plataformadioggoapi.model.Professor;
import com.example.plataformadioggoapi.repository.ProfessorRepository;
import org.bson.types.ObjectId;
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
        List<ProfessorResponseDTO> professores = professorRepository.listarProfessoresComNomeDisciplinas();

        return professores;
    }

    public ProfessorResponseDTO buscarPorId(ObjectId id) {
        ProfessorResponseDTO professor = professorRepository.buscarProfessorComNomeDisciplinas(id)
                .orElseThrow(() -> new RuntimeException("Professor de ID " + id + " não encontrado."));

        return professor;
    }

    public ProfessorResponseDTO criarProfessor(ProfessorRequestDTO professorDTO) {

        String senhaHash = passwordEncoder.encode(professorDTO.getSenha());
        professorDTO.setSenha(senhaHash);

        Professor novoProfessor = professorMapper.toEntity(professorDTO);

        Professor salvo = professorRepository.save(novoProfessor);

        return professorMapper.toDTO(salvo);
    }

    public ProfessorResponseDTO atualizarProfessor(ObjectId id, ProfessorRequestDTO professorDTO) {
        Professor existente = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor de ID " + id + " não encontrado."));

        professorMapper.updateEntityFromDTO(professorDTO, existente);

        Professor salvo = professorRepository.save(existente);

        return professorMapper.toDTO(salvo);
    }

    public ProfessorResponseDTO atualizarParcialProfessor(ObjectId id, ProfessorRequestDTO professorDTO) {
        Professor existente = professorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professor de ID " + id + " não encontrado."));

        if (professorDTO.getUsuario() != null && !professorDTO.getUsuario().isBlank()) {
            existente.setUsuario(professorDTO.getUsuario());
        }

        if (professorDTO.getSenha() != null && !professorDTO.getSenha().isBlank()) {
            existente.setSenha(passwordEncoder.encode(professorDTO.getSenha()));
        }

        if (professorDTO.getDisciplinas() != null && !professorDTO.getDisciplinas().isEmpty()) {
            existente.setDisciplinas(professorDTO.getDisciplinas());
        }

        Professor salvo = professorRepository.save(existente);

        return professorMapper.toDTO(salvo);
    }

    public void deletarProfessor(ObjectId id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor de ID " + id + " não encontrado.");
        }
        professorRepository.deleteById(id);
    }
}
