package com.adolfo.cordoba.test.gs.services;

import com.adolfo.cordoba.test.gs.entities.postgres.UsuarioRol;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioRolService {
    public List<UsuarioRol> findAll();
    public UsuarioRol insertUsuarioRol(Long idRol, Long idUsuario);
    public Optional<UsuarioRol> findById(Long id);
    public void deleteUsuarioRol(Long id);
}
