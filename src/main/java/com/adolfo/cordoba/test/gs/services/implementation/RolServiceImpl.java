package com.adolfo.cordoba.test.gs.services.implementation;

import com.adolfo.cordoba.test.gs.entities.postgres.Rol;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.RolDto;
import com.adolfo.cordoba.test.gs.repository.RolRepository;
import com.adolfo.cordoba.test.gs.services.RolService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RolServiceImpl implements RolService {
    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public List<Rol> findAllRolls() {
        List<Rol> list = null;

        try {
            list = this.rolRepository.findAll();
        } catch (Exception e) {
            log.info("Error en findAllRolls: " + e.getMessage());
        }

        return list;
    }

    @Override
    public Rol insertRol(RolDto entity) {
        Rol rol = null;

        try {
            Rol newRol = new Rol();

            newRol.setTipo(entity.getTipo());

            rol = this.rolRepository.save(newRol);
        } catch (Exception e) {
            log.info("Error en insertRol: " + e.getMessage());
        }

        return rol;
    }

    @Override
    public Optional<Rol> findById(Long id) {
        Optional<Rol> rol = Optional.empty();

        try {
            rol = Optional.ofNullable(rolRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Rol no encontrado")));
        } catch (Exception e) {
            log.info("Error en findById: " + e.getMessage());
        }

        return rol;
    }

    @Override
    public Rol updateRol(Rol entity) {
        Rol rol = null;

        try {
            Rol existingRol = rolRepository.findById(entity.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

            existingRol.setTipo(entity.getTipo());

            rol = rolRepository.save(existingRol);
        } catch (Exception e) {
            log.info("Error en updateRol: " + e.getMessage());
        }

        return rol;
    }

    @Override
    public void deleteRol(Long id) {
        try {
            rolRepository.deleteById(id);
        } catch (Exception e) {
            log.info("Error en deleteRol: " + e.getMessage());
        }
    }
}
