package com.adolfo.cordoba.test.gs.services;

import com.adolfo.cordoba.test.gs.entities.postgres.Usuario;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.UsuarioDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsuarioService {
    public List<Usuario> findAllUsers();
    public Usuario insertUser(UsuarioDto entity);
    public Optional<Usuario> findByUsuario(String usuario);
    Usuario updateUserByUsuario(UsuarioDto entity);
    public void deleteUser(String usuario);
}
