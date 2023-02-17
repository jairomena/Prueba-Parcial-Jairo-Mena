package com.programacion.distribuida.db;

import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Schema(name = "AUTHOR", description = "Nombre y Apellido de Autores")
public class Authors {
    @Id
    @Getter @Setter  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name")
    @Getter @Setter  @Schema(required = true)  private String firtName;

    @Column(name = "last_name")
    @Getter @Setter @Schema(required = true) private String lastName;
}