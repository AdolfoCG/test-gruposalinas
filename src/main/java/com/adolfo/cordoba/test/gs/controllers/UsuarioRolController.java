package com.adolfo.cordoba.test.gs.controllers;

import com.adolfo.cordoba.test.gs.entities.postgres.UsuarioRol;
import com.adolfo.cordoba.test.gs.services.UsuarioRolService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarioRol")
@AllArgsConstructor
public class UsuarioRolController {
    private UsuarioRolService usuarioRolService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findUsersRols() {
        try {
            List<UsuarioRol> usuarioRols = usuarioRolService.findAll();

            return new ResponseEntity<>(usuarioRols, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/insertUsuarioRols/{idRol}/{idUsuario}")
    public ResponseEntity<?> insertUsersRols(@PathVariable(name = "idRol") Long idRol, @PathVariable(name = "idUsuario") Long idUsuario) {
        try {
            UsuarioRol usuarioRol = usuarioRolService.insertUsuarioRol(idRol, idUsuario);

            return new ResponseEntity<UsuarioRol>(usuarioRol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getUsersRols(@PathVariable(name = "id") Long id) {
        Optional<UsuarioRol> usuarioRol = usuarioRolService.findById(id);


        if (usuarioRol.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuarioRol);
        }
    }

    @DeleteMapping(value = "/deleteUsuarioRol/{id}")
    public ResponseEntity<?> deleteUsersRols(@PathVariable(name = "id") Long id) {
        try {
            usuarioRolService.deleteUsuarioRol(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
