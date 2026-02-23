package com.example.plataformadioggoapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AdminRequestDTO {
    private String email;
    private String senha;
}
