package com.adolfo.cordoba.test.gs.repository;

import com.adolfo.cordoba.test.gs.entities.postgres.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {
}
