package com.adolfo.cordoba.test.gs.controllers;

import com.adolfo.cordoba.test.gs.entities.postgres.Rol;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.RolDto;
import com.adolfo.cordoba.test.gs.services.RolService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {
    private final RolService rolService;
    public RolController(RolService rolService) {
        this.rolService = rolService;
    }

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findRolls() {
        try {
            List<Rol> rol = rolService.findAllRolls();

            return new ResponseEntity<>(rol, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/insertRol")
    public ResponseEntity<?> insertRol(@RequestBody RolDto entity) {
        try {
            Rol rol = rolService.insertRol(entity);

            return new ResponseEntity<Rol>(rol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getRolById(@PathVariable(name = "id") Long id) {
       Optional<Rol> rol = rolService.findById(id);


       if (rol.isEmpty()) {
           return ResponseEntity.notFound().build();
       } else {
            return ResponseEntity.ok(rol);
       }
    }

    @PutMapping(value = "/updateRol")
    public ResponseEntity<?> updateRol(@RequestBody Rol entity) {
        try {
            Rol rol = rolService.updateRol(entity);

            return new ResponseEntity<Rol>(rol, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deleteRol/{id}")
    public ResponseEntity<?> deleteRol(@PathVariable(name = "id") Long id) {
        try {
            rolService.deleteRol(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
