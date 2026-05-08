package com.sanosysalvos.ms_publicacion.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI publicacionOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MS Publicacion API")
                        .version("1.0.0")
                        .description("Microservicio REST para la gestion de publicaciones de mascotas")
                        .contact(new Contact().name("Sanos y Salvos")));
    }
}
