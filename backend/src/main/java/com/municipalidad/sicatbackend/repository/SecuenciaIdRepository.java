package com.municipalidad.sicatbackend.repository;

import com.municipalidad.sicatbackend.entity.SecuenciaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SecuenciaIdRepository extends JpaRepository<SecuenciaId, String> {

    // Incrementa el correlativo y lo devuelve en el mismo paso (atómico)
    @Modifying
    @Query(value = """
            UPDATE sicat.secuencia_id
            SET ultimo_numero = ultimo_numero + 1
            WHERE prefijo = :prefijo
            """, nativeQuery = true)
    void incrementar(@Param("prefijo") String prefijo);
}
