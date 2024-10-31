package com.adolfo.cordoba.test.gs.repository;

import com.adolfo.cordoba.test.gs.entities.postgres.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuario(String usuario);
    @Query(value = "DELETE FROM usuario WHERE usuario = :usuario", nativeQuery = true)
    void deleteByUsuario(@Param("usuario") String usuario);
}
