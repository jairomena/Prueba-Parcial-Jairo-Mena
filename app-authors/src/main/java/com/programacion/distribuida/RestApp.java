package com.programacion.distribuida;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
@OpenAPIDefinition(
        info = @Info(
                title = "Documentación del CRUD",
                version = "1.0.1"
        )
)
@ApplicationPath("/")
public class RestApp extends Application {
}