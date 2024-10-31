package com.adolfo.cordoba.test.gs.services;

import com.adolfo.cordoba.test.gs.entities.postgres.Rol;
import com.adolfo.cordoba.test.gs.entities.postgres.dto.RolDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RolService {
    public List<Rol> findAllRolls();
    public Rol insertRol(RolDto entity);
    public Optional<Rol> findById(Long id);
    public Rol updateRol(Rol entity);
    public void deleteRol(Long id);
}
