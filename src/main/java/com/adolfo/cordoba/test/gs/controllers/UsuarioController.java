package com.adolfo.cordoba.test.gs.controllers;

import com.adolfo.cordoba.test.gs.entities.postgres.Usuario;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.UsuarioDto;
import com.adolfo.cordoba.test.gs.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findUsers() {
        try {
            List<Usuario> usuarios = usuarioService.findAllUsers();

            return new ResponseEntity<>(usuarios, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/insertUsuario")
    public ResponseEntity<?> insertUsuario(@RequestBody UsuarioDto entity) {
        try {
            Usuario usuario = usuarioService.insertUser(entity);

            return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findByUsuario/{usuario}")
    public ResponseEntity<?> getUsuarioByUsuario(@PathVariable(name = "usuario") String usuario) {
        Optional<Usuario> user = usuarioService.findByUsuario(usuario);


        if (user.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(user);
        }
    }

    @PutMapping(value = "/updateUsuario")
    public ResponseEntity<?> updateUsuario(@RequestBody UsuarioDto entity) {
        try {
            Usuario usuario = usuarioService.updateUserByUsuario(entity);

            return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteUsuario/{usuario}")
    public ResponseEntity<?> deleteRol(@PathVariable(name = "usuario") String usuario) {
        try {
            usuarioService.deleteUser(usuario);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
