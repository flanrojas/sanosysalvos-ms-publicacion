package com.sanosysalvos.ms_publicacion.service;

import com.sanosysalvos.ms_publicacion.model.Publicacion;
import com.sanosysalvos.ms_publicacion.repository.PublicacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PublicacionServiceTest {

    @Mock
    private PublicacionRepository  publicacionRepository;

    @InjectMocks
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
    void getById_WhenPetExists_ShouldReturnPet() {
        when(publicacionRepository.findById(sampleId)).thenReturn(Optional.of(samplePublicacion));

        Publicacion result = publicacionService.getById(sampleId);

        assertNotNull(result);
        assertEquals("Perrito perdido en el parque", result.getTitulo());
        verify(publicacionRepository, times(1)).findById(sampleId);
    }

    @Test
    void getById_WhenPetDoesNotExist_ShouldThrowException() {
        when(publicacionRepository.findById(sampleId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            publicacionService.getById(sampleId);
        });

        assertTrue(exception.getMessage().contains("Publicacion no encontrada"));
        verify(publicacionRepository, times(1)).findById(sampleId);
    }

    @Test
    void getAll_ShouldReturnList() {
        when(publicacionRepository.findAll()).thenReturn(List.of(samplePublicacion));

        List<Publicacion> result = publicacionService.getAll();

        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
    }

    @Test
    void update_WhenPetExists_ShouldReturnUpdatedPet() {
        when(publicacionRepository.findById(sampleId)).thenReturn(Optional.of(samplePublicacion));
        when(publicacionRepository.save(any(Publicacion.class))).thenReturn(samplePublicacion);

            Publicacion result = publicacionService.update(sampleId, samplePublicacion);

        assertNotNull(result);
        verify(publicacionRepository).save(any(Publicacion.class));
    }

    @Test
    void update_WhenPetDoesNotExist_ShouldThrowException() {
        when(publicacionRepository.findById(sampleId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            publicacionService.update(sampleId, samplePublicacion);
        });

        assertTrue(exception.getMessage().contains("Publicacion no encontrada con id: " + sampleId));
        verify(publicacionRepository, times(1)).findById(sampleId);
        verify(publicacionRepository, never()).save(any(Publicacion.class));
    }

    @Test
    void create_ShouldReturnSavedPet() {
        when(publicacionRepository.save(any(Publicacion.class))).thenReturn(samplePublicacion);

        Publicacion result = publicacionService.create(samplePublicacion);

        assertNotNull(result);
        assertEquals("Perrito perdido en el parque", result.getTitulo());

        assertNull(samplePublicacion.getIdPublicacion());

        verify(publicacionRepository, times(1)).save(any(Publicacion.class));
    }

    @Test
    void delete_WhenPetExists_ShouldDeletePet() {
        when(publicacionRepository.findById(sampleId)).thenReturn(Optional.of(samplePublicacion));

        publicacionService.delete(sampleId);

        verify(publicacionRepository, times(1)).findById(sampleId);
        verify(publicacionRepository, times(1)).delete(samplePublicacion);
    }

    @Test
    void delete_WhenPetDoesNotExist_ShouldThrowException() {
        when(publicacionRepository.findById(sampleId)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            publicacionService.delete(sampleId);
        });

        assertTrue(exception.getMessage().contains("Publicacion no encontrada con id: " + sampleId));
        verify(publicacionRepository, times(1)).findById(sampleId);
        verify(publicacionRepository, never()).delete(any(Publicacion.class));
    }

}
