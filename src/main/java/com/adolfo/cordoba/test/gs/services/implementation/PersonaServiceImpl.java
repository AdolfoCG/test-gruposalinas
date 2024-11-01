package com.adolfo.cordoba.test.gs.services.implementation;

import com.adolfo.cordoba.test.gs.entities.postgres.Persona;
import com.adolfo.cordoba.test.gs.entities.postgres.Usuario;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.PersonaDto;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.UsuarioDto;
import com.adolfo.cordoba.test.gs.repository.PersonaRepository;
import com.adolfo.cordoba.test.gs.services.PersonaService;
import com.adolfo.cordoba.test.gs.services.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public List<Persona> findAllPersonas() {
        List<Persona> list = null;

        try {
            list = this.personaRepository.findAll();
        } catch (Exception e) {
            log.info("Error en findAllPersonas: " + e.getMessage());
        }

        return list;
    }

    @Override
    public Persona insertPersona(PersonaDto entity) {
        Persona persona = null;

        try {
            Persona newPersona = new Persona();

            newPersona.setNombres(entity.getNombres());
            newPersona.setApellidos(entity.getApellidos());
            newPersona.setSexo(entity.getSexo());
            newPersona.setPais(entity.getPais());
            newPersona.setDireccion(entity.getDireccion());

            Usuario usuario = usuarioService.insertUser(entity.getUsuarioDto());

            newPersona.setUsuario(usuario);

            persona = this.personaRepository.save(newPersona);
        } catch (Exception e) {
            log.info("Error en insertPersona: " + e.getMessage());
        }

        return persona;
    }

    @Override
    public Optional<Persona> findById(Long id) {
        Optional<Persona> persona = Optional.empty();

        try {
            persona = Optional.ofNullable(personaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Persona no encontrada")));
        } catch (Exception e) {
            log.info("Error en findById: " + e.getMessage());
        }

        return persona;
    }

    @Override
    public Persona updatePersonaById(Persona entity) {
        Persona persona = null;

        try {
            Persona existingPerson = personaRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

            existingPerson.setNombres(entity.getNombres());
            existingPerson.setApellidos(entity.getApellidos());
            existingPerson.setSexo(entity.getSexo());
            existingPerson.setPais(entity.getPais());
            existingPerson.setDireccion(entity.getDireccion());

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setUsuario(entity.getUsuario().getUsuario());
            usuarioDto.setContrasena(entity.getUsuario().getContrasena());
            usuarioDto.setEstado(entity.getUsuario().getEstado());

            Usuario usuario = usuarioService.updateUserByUsuario(usuarioDto);

            existingPerson.setUsuario(usuario);

            persona = personaRepository.save(existingPerson);
        } catch (Exception e) {
            log.info("Error en updatePersonaById: " + e.getMessage());
        }

        return persona;
    }

    @Override
    public void deletePersona(Long id) {
        try {
            personaRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Error en deletePersona: " + e.getMessage());
        }
    }
}
