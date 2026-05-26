package com.celsia.celsiainternet_api.services;

import com.celsia.celsiainternet_api.domain.entity.Cliente;
import com.celsia.celsiainternet_api.domain.entity.Servicio;
import com.celsia.celsiainternet_api.dto.ClienteDTO;
import com.celsia.celsiainternet_api.dto.ServicioDTO;
import com.celsia.celsiainternet_api.repositories.ClienteRepository;
import com.celsia.celsiainternet_api.repositories.ServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ServicioRepository servicioRepository;

    @Transactional
    public Cliente crearCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setIdentificacion(dto.identificacion());
        cliente.setNombres(dto.nombres());
        cliente.setApellidos(dto.apellidos());
        cliente.setTipoIdentificacion(dto.tipoIdentificacion());
        cliente.setFechaNacimiento(dto.fechaNacimiento());
        cliente.setNumeroCelular(dto.numeroCelular());
        cliente.setCorreoElectronico(dto.correoElectronico());

        return clienteRepository.save(cliente);
    }

    @Transactional
    public Servicio registrarServicio(ServicioDTO dto) {
        // Validamos que el cliente exista antes de asignarle un servicio
        clienteRepository.findById(dto.identificacion())
                .orElseThrow(() -> new IllegalArgumentException("El cliente con identificación " + dto.identificacion() + " no existe."));

        Servicio servicio = new Servicio();
        servicio.setIdentificacion(dto.identificacion());
        servicio.setServicio(dto.servicio().name());
        servicio.setFechaInicio(dto.fechaInicio());
        servicio.setUltimaFacturacion(dto.ultimaFacturacion());
        servicio.setUltimoPago(0);

        return servicioRepository.save(servicio);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> consultarClienteConServicios(String identificacion) {
        Cliente cliente = clienteRepository.findById(identificacion)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        List<Servicio> servicios = servicioRepository.findByIdentificacion(identificacion);

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("cliente", cliente);
        respuesta.put("servicios", servicios);

        return respuesta;
    }
}
