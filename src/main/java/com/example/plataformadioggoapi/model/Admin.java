package com.example.plataformadioggoapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Data
@AllArgsConstructor
public class Admin {
    @Id
    @Field("_id")
    private String id;
    private String email;
    private String senha;
}
