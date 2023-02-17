package com.programacion.distribuida.rest;

import com.programacion.distribuida.db.Authors;
import com.programacion.distribuida.servicios.AuthorRepository;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorRest {
    @Inject AuthorRepository repository;
    @GET
    @Operation(
            summary = "Lista un autor"
    )
    @APIResponses(
            { @APIResponse(
                    responseCode = "200",
                    description = "Autor listado correctamente"

            ),
                    @APIResponse(
                            responseCode = "404",
                            description = "Error autor no listado",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Authors.class)))}
    )

    @Path("/{id}")
    public Authors findById(@PathParam("id") Integer id) {
        return repository.findById(id);
    }



    @GET
    @Operation(
            summary = "Lista de autores"
    )
    @APIResponses(
            { @APIResponse(
                    responseCode = "200",
                    description = "Autores listados correctamente"
            ),
                    @APIResponse(
                            responseCode = "404",
                            description = "Error autores no listados",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                                    schema = @Schema(implementation = Authors.class, type = SchemaType.ARRAY)))}
    )

    public List<Authors> findAll() {
        return repository
                .findAll()
                .list();
    }

    @POST
    @Operation(
            summary = "Creamos un autor",
            description = "Crea un autor"
    )
    public void insert(Authors obj) {
        repository.persist(obj);
    }
    @PUT
    @Operation(
            summary = "Actualizamos un autor",
            description = "Actualiza un autor"
    )
    @Path("/{id}")
    public void update(Authors obj, @PathParam("id") Integer id) {

        var author = repository.findById(id);

        author.setFirtName(obj.getFirtName());
        author.setLastName(obj.getLastName());
    }

    @DELETE
    @Operation(
            summary = "Borramos un autor",
            description = "Borra un autor"
    )
    @Path("/{id}")
    public void delete( @PathParam("id") Integer id ) {
        repository.deleteById(id);
    }
}
