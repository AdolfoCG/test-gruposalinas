package com.adolfo.cordoba.test.gs.entities.postgres.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonaDto {
    private String nombres;
    private String apellidos;
    private Character sexo;
    private String pais;
    private String direccion;
    private UsuarioDto usuarioDto;
}
