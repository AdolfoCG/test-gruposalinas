package com.adolfo.cordoba.test.gs.services.implementation;

import com.adolfo.cordoba.test.gs.entities.postgres.*;
import com.adolfo.cordoba.test.gs.repository.RolRepository;
import com.adolfo.cordoba.test.gs.repository.UsuarioRepository;
import com.adolfo.cordoba.test.gs.repository.UsuarioRolRepository;
import com.adolfo.cordoba.test.gs.services.UsuarioRolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class UsuarioRolServiceImpl implements UsuarioRolService {
    private final UsuarioRolRepository usuarioRolRepository;
    private final RolRepository rolRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioRol> findAll() {
        List<UsuarioRol> list = null;

        try {
            list = this.usuarioRolRepository.findAll();
        } catch (Exception e) {
            log.info("Error en findAll: " + e.getMessage());
        }

        return list;
    }

    @Override
    public UsuarioRol insertUsuarioRol(Long idRol, Long idUsuario) {
        UsuarioRol usuarioRol = new UsuarioRol();

        try {
            Rol rol = rolRepository.findById(idRol).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));
            Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

            usuarioRol.setRol(rol);
            usuarioRol.setUsuario(usuario);
            return usuarioRolRepository.save(usuarioRol);

        } catch (Exception e) {
            log.info("Error en insertUsuarioRol: " + e.getMessage());
        }

        return usuarioRol;
    }

    @Override
    public Optional<UsuarioRol> findById(Long id) {
        Optional<UsuarioRol> usuarioRol = Optional.empty();

        try {
            usuarioRol = Optional.ofNullable(usuarioRolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("UsuarioRol no encontrado")));
        } catch (Exception e) {
            log.info("Error en findById: " + e.getMessage());
        }

        return usuarioRol;
    }

    @Override
    public void deleteUsuarioRol(Long id) {
        try {
            usuarioRolRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Error en deleteUsuarioRol: " + e.getMessage());
        }
    }
}
