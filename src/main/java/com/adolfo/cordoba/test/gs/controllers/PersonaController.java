package com.adolfo.cordoba.test.gs.controllers;

import com.adolfo.cordoba.test.gs.entities.postgres.Persona;
import com.adolfo.cordoba.test.gs.entities.postgres.Rol;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.PersonaDto;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.RolDto;
import com.adolfo.cordoba.test.gs.services.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    @Autowired
    private PersonaService personaService;

    @GetMapping(value = "/findAll")
    public ResponseEntity<?> findPeople() {
        try {
            List<Persona> personas = personaService.findAllPersonas();

            return new ResponseEntity<>(personas, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getCause().toString(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/insertPersona")
    public ResponseEntity<?> insertPeople(@RequestBody PersonaDto entity) {
        try {
            Persona persona = personaService.insertPersona(entity);

            return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findPeopleById(@PathVariable(name = "id") Long id) {
        Optional<Persona> persona = personaService.findById(id);


        if (persona.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(persona);
        }
    }

    @PutMapping(value = "/updatePersona")
    public ResponseEntity<?> updatePeopleById(@RequestBody Persona entity) {
        try {
            Persona persona = personaService.updatePersonaById(entity);

            return new ResponseEntity<Persona>(persona, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/deletePersona/{id}")
    public ResponseEntity<?> deletePeople(@PathVariable(name = "id") Long id) {
        try {
            personaService.deletePersona(id);

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
