package com.sanosysalvos.ms_publicacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "publicaciones")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Publicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_publicacion", nullable = false, updatable = false)
    private UUID idPublicacion;

    @Column(nullable = false, length = 20)
    private String tipoPublicacion;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false, length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fechaPublicacion;

    @Column(nullable = false)
    private LocalDate fechaExtravioOEncuentro;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false)
    private Double latitud;

    @Column(nullable = false)
    private Double longitud;

    private String direccionReferencia;

    private String urlFoto;

    @Column(nullable = false)
    private String nombreContacto;

    @Column(nullable = false)
    private String telefonoContacto;

    private String emailContacto;

    @Column(nullable = false)
    private UUID mascotaId;

    @Column(nullable = false)
    private UUID usuarioId;


}
