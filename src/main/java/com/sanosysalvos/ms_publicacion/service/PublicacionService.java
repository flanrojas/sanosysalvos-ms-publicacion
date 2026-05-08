package com.sanosysalvos.ms_publicacion.service;

import com.sanosysalvos.ms_publicacion.model.Publicacion;
import com.sanosysalvos.ms_publicacion.repository.PublicacionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PublicacionService {

    private final PublicacionRepository publicacionRepository;

    public PublicacionService(PublicacionRepository publicacionRepository) {
        this.publicacionRepository = publicacionRepository;
    }

    public List<Publicacion> getAll() {
        return publicacionRepository.findAll();
    }

    public Publicacion getById(UUID id) {
        return publicacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Publicacion no encontrada con id: " + id));
    }

    public Publicacion create(Publicacion publicacion) {
        publicacion.setIdPublicacion(null);
        return publicacionRepository.save(publicacion);
    }

    public Publicacion update(UUID id, Publicacion request) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Publicacion no encontrada con id: " + id));

        publicacion.setTipoPublicacion(request.getTipoPublicacion());
        publicacion.setTitulo(request.getTitulo());
        publicacion.setDescripcion(request.getDescripcion());
        publicacion.setFechaPublicacion(request.getFechaPublicacion());
        publicacion.setFechaExtravioOEncuentro(request.getFechaExtravioOEncuentro());
        publicacion.setEstado(request.getEstado());
        publicacion.setLatitud(request.getLatitud());
        publicacion.setLongitud(request.getLongitud());
        publicacion.setDireccionReferencia(request.getDireccionReferencia());
        publicacion.setUrlFoto(request.getUrlFoto());
        publicacion.setNombreContacto(request.getNombreContacto());
        publicacion.setTelefonoContacto(request.getTelefonoContacto());
        publicacion.setEmailContacto(request.getEmailContacto());
        publicacion.setMascotaId(request.getMascotaId());
        publicacion.setUsuarioId(request.getUsuarioId());

        return publicacionRepository.save(publicacion);
    }

    public void delete(UUID id) {
        Publicacion publicacion = publicacionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Publicacion no encontrada con id: " + id));
        publicacionRepository.delete(publicacion);
    }
}
