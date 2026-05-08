package com.sanosysalvos.ms_publicacion.repository;

import com.sanosysalvos.ms_publicacion.model.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PublicacionRepository extends JpaRepository<Publicacion, UUID> {
}
