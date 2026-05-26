package com.celsia.celsiainternet_api.dto;

import com.celsia.celsiainternet_api.domain.enums.TipoIdentificacion;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ClienteDTO(
        @NotBlank(message = "La identificación no puede estar en blanco")
        String identificacion,

        @NotBlank(message = "Los nombres son obligatorios")
        String nombres,

        @NotBlank(message = "Los apellidos son obligatorios")
        String apellidos,

        @NotNull(message = "El tipo de identificación es obligatorio")
        TipoIdentificacion tipoIdentificacion,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        LocalDate fechaNacimiento,

        @NotBlank(message = "El número de celular es obligatorio")
        String numeroCelular,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El formato de correo no es válido")
        String correoElectronico
) {}