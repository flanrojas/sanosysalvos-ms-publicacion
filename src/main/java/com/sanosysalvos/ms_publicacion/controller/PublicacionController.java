package com.sanosysalvos.ms_publicacion.controller;

import com.sanosysalvos.ms_publicacion.model.Publicacion;
import com.sanosysalvos.ms_publicacion.service.PublicacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/publicaciones")
@Tag(name = "Publicaciones", description = "CRUD para gestionar publicaciones de mascotas")
public class PublicacionController {

    private final PublicacionService publicacionService;

    public PublicacionController(PublicacionService publicacionService) {
        this.publicacionService = publicacionService;
    }

    @GetMapping
    @Operation(summary = "Listar publicaciones", description = "Obtiene todas las publicaciones registradas")
    @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    public List<Publicacion> getAll() {
        return publicacionService.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener publicacion por ID", description = "Obtiene una publicacion especifica")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Publicacion encontrada"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    public Publicacion getById(@PathVariable UUID id) {
        return publicacionService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Crear publicacion", description = "Crea una nueva publicacion")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Publicacion creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida")
    })
    public Publicacion create(@RequestBody Publicacion publicacion) {
        return publicacionService.create(publicacion);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar publicacion", description = "Actualiza una publicacion existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Publicacion actualizada correctamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    public Publicacion update(@PathVariable UUID id, @RequestBody Publicacion publicacion) {
        return publicacionService.update(id, publicacion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Eliminar publicacion", description = "Elimina una publicacion por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Publicacion eliminada correctamente"),
            @ApiResponse(responseCode = "404", description = "Publicacion no encontrada")
    })
    public void delete(@PathVariable UUID id) {
        publicacionService.delete(id);
    }
}
