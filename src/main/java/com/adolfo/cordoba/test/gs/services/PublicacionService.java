package com.adolfo.cordoba.test.gs.services;

import com.adolfo.cordoba.test.gs.entities.postgres.Publicacion;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.PublicacionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PublicacionService {
    public List<Publicacion> findAllPublicaciones();
    public Publicacion insertPublicacion(PublicacionDto entity, Long idPersona);
    public Optional<Publicacion> findById(Long id);
    public Publicacion updatePublicacionById(Publicacion entity);
    public void deletePublicacion(Long id);
}
