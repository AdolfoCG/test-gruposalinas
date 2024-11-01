package com.adolfo.cordoba.test.gs.services;

import com.adolfo.cordoba.test.gs.entities.postgres.Persona;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.PersonaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PersonaService {
    public List<Persona> findAllPersonas();
    public Persona insertPersona(PersonaDto entity);
    public Optional<Persona> findById(Long id);
    public Persona updatePersonaById(Persona entity);
    public void deletePersona(Long id);
}
