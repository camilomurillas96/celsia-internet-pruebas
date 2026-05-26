package com.celsia.celsiainternet_api.domain.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoServicio {
    INTERNET_200("Internet 200 MB"),
    INTERNET_400("Internet 400 MB"),
    INTERNET_600("Internet 600 MB"),
    DIRECTV_GO("Directv Go"),
    PARAMOUNT("Paramount+"),
    WIN_PLUS("Win+");

    private final String descripcion;

    TipoServicio(String descripcion) {
        this.descripcion = descripcion;
    }

    @JsonValue
    public String getDescripcion() {
        return descripcion;
    }
}