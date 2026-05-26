package com.celsia.celsiainternet_api.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ServicioId implements Serializable {
    private String identificacion;
    private String servicio;
}
