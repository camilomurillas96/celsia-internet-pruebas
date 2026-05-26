package com.celsia.celsiainternet_api.domain.entity;

import com.celsia.celsiainternet_api.domain.enums.TipoIdentificacion;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
public class Cliente {

    @Id
    @Column(length = 20)
    @NotBlank(message = "La identificación no puede estar en blanco")
    private String identificacion;

    @Column(length = 80, nullable = false)
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombres;

    @Column(length = 80, nullable = false)
    @NotBlank(message = "El apellido no puede estar en blanco")
    private String apellidos;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoidentificacion", length = 2, nullable = false)
    @NotNull(message = "El tipo de identificación es obligatorio")
    private TipoIdentificacion tipoIdentificacion;

    @Column(name = "fechanacimiento", nullable = false)
    @NotNull(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @Column(name = "numerocelular", length = 20, nullable = false)
    @NotBlank(message = "El número de celular no puede estar en blanco")
    private String numeroCelular;

    @Column(name = "correoelectronico", length = 80, nullable = false)
    @NotBlank(message = "El correo no puede estar en blanco")
    @Email(message = "Formato de correo inválido")
    private String correoElectronico;
}