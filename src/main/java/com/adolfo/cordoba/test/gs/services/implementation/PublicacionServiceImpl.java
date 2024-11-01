package com.adolfo.cordoba.test.gs.services.implementation;

import com.adolfo.cordoba.test.gs.entities.postgres.Persona;
import com.adolfo.cordoba.test.gs.entities.postgres.Publicacion;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.PublicacionDto;
import com.adolfo.cordoba.test.gs.repository.PersonaRepository;
import com.adolfo.cordoba.test.gs.repository.PublicacionRepository;
import com.adolfo.cordoba.test.gs.services.PublicacionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class PublicacionServiceImpl implements PublicacionService {
    private final PublicacionRepository publicacionRepository;
    private final PersonaRepository personaRepository;


    @Override
    public List<Publicacion> findAllPublicaciones() {
        List<Publicacion> list = null;

        try {
            list = this.publicacionRepository.findAll();
        } catch (Exception e) {
            log.info("Error en findAllPublicaciones: " + e.getMessage());
        }

        return list;
    }

    @Override
    public Publicacion insertPublicacion(PublicacionDto entity, Long idPersona) {
        Publicacion publicacion = new Publicacion();

        try {
            Persona persona = personaRepository.findById(idPersona).orElseThrow(() -> new EntityNotFoundException("Persona no encontrada"));

            publicacion.setCuerpo(entity.getCuerpo());
            publicacion.setPersona(persona);
            return publicacionRepository.save(publicacion);

        } catch (Exception e) {
            log.info("Error en insertPublicacion: " + e.getMessage());
        }

        return publicacion;
    }

    @Override
    public Optional<Publicacion> findById(Long id) {
        Optional<Publicacion> publicacion = Optional.empty();

        try {
            publicacion = Optional.ofNullable(publicacionRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada")));
        } catch (Exception e) {
            log.info("Error en findById: " + e.getMessage());
        }

        return publicacion;
    }

    @Override
    public Publicacion updatePublicacionById(Publicacion entity) {
        Publicacion publicacion = null;

        try {
            Publicacion existingPost = publicacionRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Publicación no encontrada"));

            existingPost.setCuerpo(entity.getCuerpo());

            publicacion = publicacionRepository.save(existingPost);
        } catch (Exception e) {
            log.info("Error en updatePublicacionById: " + e.getMessage());
        }

        return publicacion;
    }

    @Override
    public void deletePublicacion(Long id) {
        try {
            publicacionRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Error en deletePublicacion: " + e.getMessage());
        }
    }
}
