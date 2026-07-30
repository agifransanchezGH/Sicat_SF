package com.municipalidad.sicatbackend;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class SicatBackendApplication {

    @PostConstruct
    public void init() {
        // Establece UTC como zona horaria predeterminada de la aplicación
        // Evita que el driver envíe "America/Buenos_Aires" a PostgreSQL
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SicatBackendApplication.class, args);
    }
}