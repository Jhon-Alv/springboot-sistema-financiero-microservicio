package com.reto.cliente_microservice.controller;

import com.reto.cliente_microservice.dto.ClienteDto;
import com.reto.cliente_microservice.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Tag(name = "Cliente", description = "Operaciones relacionadas a clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(
            summary = "Obtener cliente por código único",
            description = "Retorna los datos personales del cliente"
    )
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    @GetMapping("/{codigoUnico}")
    public Mono<ClienteDto> obtenerCliente(@PathVariable String codigoUnico){
        return clienteService.obtenerClientePorCodigoUnico(codigoUnico);
    }

}
