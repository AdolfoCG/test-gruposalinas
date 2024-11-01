package com.adolfo.cordoba.test.gs.repository;

import com.adolfo.cordoba.test.gs.entities.postgres.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
