package com.celsia.celsiainternet_api.repositories;

import com.celsia.celsiainternet_api.domain.entity.Servicio;
import com.celsia.celsiainternet_api.domain.entity.ServicioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, ServicioId> {
    // Método para buscar todos los servicios de un cliente específico
    List<Servicio> findByIdentificacion(String identificacion);
}