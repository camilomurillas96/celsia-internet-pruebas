package com.celsia.celsiainternet_api.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "servicios")
@IdClass(ServicioId.class)
@Data
@NoArgsConstructor
public class Servicio {

    @Id
    @Column(length = 20)
    private String identificacion;

    @Id
    @Column(length = 80)
    private String servicio;

    @Column(name = "fechainicio", nullable = false)
    @NotNull(message = "La fecha de inicio es obligatoria")
    private LocalDate fechaInicio;

    @Column(name = "ultimafacturacion", nullable = false)
    @NotNull(message = "La fecha de última facturación es obligatoria")
    private LocalDate ultimaFacturacion;

    @Column(name = "ultimopago", nullable = false)
    private Integer ultimoPago = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identificacion", insertable = false, updatable = false)
    @JsonIgnore
    private Cliente cliente;
}