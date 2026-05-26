package com.celsia.celsiainternet_api.dto;

import com.celsia.celsiainternet_api.domain.enums.TipoServicio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ServicioDTO(
        @NotBlank(message = "La identificación del cliente es obligatoria")
        String identificacion,

        @NotNull(message = "El servicio es obligatorio")
        TipoServicio servicio,

        @NotNull(message = "La fecha de inicio es obligatoria")
        LocalDate fechaInicio,

        @NotNull(message = "La última facturación es obligatoria")
        LocalDate ultimaFacturacion
) {}
