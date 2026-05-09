package com.sanosysalvos.ms_publicacion.service;

import com.sanosysalvos.ms_publicacion.model.Publicacion;
import com.sanosysalvos.ms_publicacion.repository.PublicacionRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class PublicacionServiceTest {

    @Mock
    private PublicacionRepository  publicacionRepository;

    @InjectMocks
    private PublicacionService publicacionService;

    private Publicacion samplePublicacion;
    private UUID sampleId;

    void setUp() {
        sampleId = UUID.randomUUID();
        Publicacion publicacion = Publicacion.builder()
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


}
