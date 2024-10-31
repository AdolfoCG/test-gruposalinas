package com.adolfo.cordoba.test.gs.repository;

import com.adolfo.cordoba.test.gs.entities.postgres.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
}
