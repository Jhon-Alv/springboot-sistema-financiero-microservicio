package com.reto.bff.controller;

import com.reto.bff.dto.ClienteConProductosDto;
import com.reto.bff.encryption.EncryptionService;
import com.reto.bff.service.ClienteProductoBffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/bff/clientes")
@Tag(name = "BFF Cliente y Productos", description = "Interfaz que expone información combinada de clientes y productos")
public class ClienteProductoBffController {

    @Autowired
    private ClienteProductoBffService bffService;

    @Autowired
    private EncryptionService encryptionService;


    @GetMapping("/{codigoUnicoEncriptado}")
    @Operation(
            summary = "Obtener cliente y sus productos financieros",
            description = "Devuelve la información del cliente y la lista de productos financieros asociados. El código único recibido en el path debe estar encriptado."
    )
    public Mono<ClienteConProductosDto> obtenerClienteConProducto(@PathVariable String codigoUnicoEncriptado){
        String codigoDesencriptado = encryptionService.decrypt(codigoUnicoEncriptado);
        return bffService.obtenerInfoClienteConProductos(codigoDesencriptado);
    }

}
