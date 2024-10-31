package com.adolfo.cordoba.test.gs.entities.postgres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {
    private String usuario;
    private String contrasena;
    private String estado;
}
