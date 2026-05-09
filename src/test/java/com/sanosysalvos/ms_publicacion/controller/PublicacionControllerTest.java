package com.sanosysalvos.ms_publicacion.controller;


import com.sanosysalvos.ms_publicacion.model.Publicacion;
import com.sanosysalvos.ms_publicacion.service.PublicacionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PublicacionController.class)
public class PublicacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private PublicacionService publicacionService;

    private Publicacion samplePublicacion;
    private UUID sampleId;

    @BeforeEach
    void setUp() {
        sampleId = UUID.randomUUID();
        samplePublicacion = Publicacion.builder()
                .idPublicacion(sampleId)
                .tipoPublicacion("EXTRAVIO")
                .titulo("Perrito perdido en el parque")
                .descripcion("Perrito mestizo, color caramelo, tamaño mediano. Llevaba un collar azul sin placa.")
                .fechaPublicacion(LocalDateTime.now())
                .fechaExtravioOEncuentro(LocalDate.now().minusDays(2))
                .estado("ACTIVO")
                .latitud(-33.68909)
                .longitud(-71.21528)
                .direccionReferencia("Cerca de la plaza principal, frente a la farmacia")
                .urlFoto("https://ejemplo.com/imagenes/perrito_perdido.jpg")
                .nombreContacto("Carlos Mendoza")
                .telefonoContacto("+56912345678")
                .emailContacto("carlos.mendoza@ejemplo.com")
                .mascotaId(UUID.randomUUID())
                .usuarioId(UUID.randomUUID())
                .build();
    }

    @Test
    void getAll_ShouldReturnListOfPublicacionesAndStatus200() throws Exception {
        when(publicacionService.getAll()).thenReturn(List.of(samplePublicacion));

        mockMvc.perform(get("/publicaciones"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].titulo").value("Perrito perdido en el parque"));
    }

    @Test
    void getById_WhenExists_ShouldReturnPublicacionAndStatus200() throws Exception {
        when(publicacionService.getById(sampleId)).thenReturn(samplePublicacion);

        mockMvc.perform(get("/publicaciones/{id}", sampleId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titulo").value("Perrito perdido en el parque"))
                .andExpect(jsonPath("$.tipoPublicacion").value("EXTRAVIO"));
    }

    @Test
    void getById_WhenDoesNotExist_ShouldReturnStatus404() throws Exception {
        when(publicacionService.getById(sampleId))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/publicaciones/{id}", sampleId))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_ShouldReturnSavedPublicacionAndStatus201() throws Exception {
        when(publicacionService.create(any(Publicacion.class))).thenReturn(samplePublicacion);

        mockMvc.perform(post("/publicaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePublicacion)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titulo").value("Perrito perdido en el parque"));
    }

    @Test
    void update_ShouldReturnUpdatedPublicacionAndStatus200() throws Exception {
        when(publicacionService.update(eq(sampleId), any(Publicacion.class))).thenReturn(samplePublicacion);

        mockMvc.perform(put("/publicaciones/{id}", sampleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(samplePublicacion)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Perrito perdido en el parque"));
    }

    @Test
    void delete_WhenExists_ShouldReturnStatus200() throws Exception {
        doNothing().when(publicacionService).delete(sampleId);

        mockMvc.perform(delete("/publicaciones/{id}", sampleId))
                .andExpect(status().isOk());
    }

    @Test
    void delete_WhenDoesNotExist_ShouldReturnStatus404() throws Exception {
        doThrow(new ResponseStatusException(HttpStatus.NOT_FOUND))
                .when(publicacionService).delete(sampleId);

        mockMvc.perform(delete("/publicaciones/{id}", sampleId))
                .andExpect(status().isNotFound());
    }

}
