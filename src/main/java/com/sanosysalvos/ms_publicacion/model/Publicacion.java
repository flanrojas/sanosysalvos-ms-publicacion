package com.sanosysalvos.ms_publicacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "publicaciones")
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

    public UUID getIdPublicacion() {
        return idPublicacion;
    }

    public void setIdPublicacion(UUID idPublicacion) {
        this.idPublicacion = idPublicacion;
    }

    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    public void setTipoPublicacion(String tipoPublicacion) {
        this.tipoPublicacion = tipoPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public LocalDate getFechaExtravioOEncuentro() {
        return fechaExtravioOEncuentro;
    }

    public void setFechaExtravioOEncuentro(LocalDate fechaExtravioOEncuentro) {
        this.fechaExtravioOEncuentro = fechaExtravioOEncuentro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getDireccionReferencia() {
        return direccionReferencia;
    }

    public void setDireccionReferencia(String direccionReferencia) {
        this.direccionReferencia = direccionReferencia;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getTelefonoContacto() {
        return telefonoContacto;
    }

    public void setTelefonoContacto(String telefonoContacto) {
        this.telefonoContacto = telefonoContacto;
    }

    public String getEmailContacto() {
        return emailContacto;
    }

    public void setEmailContacto(String emailContacto) {
        this.emailContacto = emailContacto;
    }

    public UUID getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(UUID mascotaId) {
        this.mascotaId = mascotaId;
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
    }
}
