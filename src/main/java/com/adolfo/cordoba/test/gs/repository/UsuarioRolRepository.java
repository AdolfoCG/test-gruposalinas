package com.adolfo.cordoba.test.gs.repository;

import com.adolfo.cordoba.test.gs.entities.postgres.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
}
