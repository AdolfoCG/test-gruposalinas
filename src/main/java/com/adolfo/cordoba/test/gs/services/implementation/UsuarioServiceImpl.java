package com.adolfo.cordoba.test.gs.services.implementation;

import com.adolfo.cordoba.test.gs.entities.postgres.Usuario;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.UsuarioDto;
import com.adolfo.cordoba.test.gs.repository.UsuarioRepository;
import com.adolfo.cordoba.test.gs.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAllUsers() {
        List<Usuario> list = null;

        try {
            list = this.usuarioRepository.findAll();
        } catch (Exception e) {
            log.info("Error en findAllUsers: " + e.getMessage());
        }

        return list;
    }

    @Override
    public Usuario insertUser(UsuarioDto entity) {
        Usuario user = null;

        try {
            Usuario newUser = new Usuario();

            newUser.setUsuario(entity.getUsuario());
            newUser.setContrasena(hashPassword(entity.getContrasena()));
            newUser.setEstado(entity.getEstado());

            user = this.usuarioRepository.save(newUser);
        } catch (Exception e) {
            log.info("Error en insertUser: " + e.getMessage());
        }

        return user;
    }

    @Override
    public Optional<Usuario> findByUsuario(String usuario) {
        Optional<Usuario> user = Optional.empty();

        try {
            user = Optional.ofNullable(usuarioRepository.findByUsuario(String.valueOf(usuario)).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado")));
        } catch (Exception e) {
            log.info("Error en findByUsuario: " + e.getMessage());
        }

        return user;
    }

    @Override
    public Usuario updateUserByUsuario(UsuarioDto entity) {
        Usuario usuario = null;

        try {
            Usuario existingUser = usuarioRepository.findByUsuario(entity.getUsuario())
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

            existingUser.setContrasena(hashPassword(entity.getContrasena()));
            existingUser.setEstado(entity.getEstado());

            usuario = usuarioRepository.save(existingUser);
        } catch (Exception e) {
            log.info("Error en updateUserByUsuario: " + e.getMessage());
        }

        return usuario;
    }

    @Override
    public void deleteUser(String usuario) {
        try {
            usuarioRepository.deleteByUsuario(usuario);
        } catch (Exception e) {
            log.info("Error en deleteUsuario: " + e.getMessage());
        }
    }

    private String hashPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
