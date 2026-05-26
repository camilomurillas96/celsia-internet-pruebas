package com.celsia.celsiainternet_api.controllers;

import com.celsia.celsiainternet_api.dto.ClienteDTO;
import com.celsia.celsiainternet_api.dto.ServicioDTO;
import com.celsia.celsiainternet_api.services.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*") // Permite la conexión desde React
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<?> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<>(clienteService.crearCliente(clienteDTO), HttpStatus.CREATED);
    }

    @PostMapping("/servicios")
    public ResponseEntity<?> registrarServicio(@Valid @RequestBody ServicioDTO servicioDTO) {
        try {
            return new ResponseEntity<>(clienteService.registrarServicio(servicioDTO), HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/clientes/{identificacion}/servicios")
    public ResponseEntity<?> consultarCliente(@PathVariable String identificacion) {
        try {
            return new ResponseEntity<>(clienteService.consultarClienteConServicios(identificacion), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("error", e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }
}
