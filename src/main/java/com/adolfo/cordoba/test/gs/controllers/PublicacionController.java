package com.adolfo.cordoba.test.gs.controllers;

import com.adolfo.cordoba.test.gs.entities.postgres.Publicacion;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.PublicacionDto;
import com.adolfo.cordoba.test.gs.services.PublicacionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicacion")
@AllArgsConstructor
public class PublicacionController {
    private final PublicacionService publicacionService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findPosts() {
        try {
            List<Publicacion> publicaciones = publicacionService.findAllPublicaciones();

            return new ResponseEntity<>(publicaciones, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/insertPublicacion/{personaId}")
    public ResponseEntity<?> insertPosts(@RequestBody PublicacionDto entity, @PathVariable(name = "personaId") Long personaId) {
        try {
            Publicacion publicacion = publicacionService.insertPublicacion(entity, personaId);

            return new ResponseEntity<Publicacion>(publicacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> getPostById(@PathVariable(name = "id") Long id) {
        Optional<Publicacion> publicacion = publicacionService.findById(id);


        if (publicacion.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(publicacion);
        }
    }

    @PutMapping(value = "/updatePublicacion")
    public ResponseEntity<?> updatePostById(@RequestBody Publicacion entity) {
        try {
            Publicacion publicacion = publicacionService.updatePublicacionById(entity);

            return new ResponseEntity<Publicacion>(publicacion, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deletePublicacion/{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        try {
            publicacionService.deletePublicacion(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
