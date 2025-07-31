package com.reto.productos_microservice.controller;

import com.reto.productos_microservice.dto.ProductoFinancieroDto;
import com.reto.productos_microservice.service.ProductoFinancieroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Producto Financiero", description = "Operaciones relacionadas a productos financieros")
public class ProductoFinancieroController {

    @Autowired
    private ProductoFinancieroService productoService;

    @GetMapping(path = "/{codigoUnico}")
    @Operation(summary = "Listar productos financieros", description = "Obtiene la lista de productos financieros de un cliente según el código único")
    @ApiResponse(responseCode = "200", description = "Procuctos encontrados")
    @ApiResponse(responseCode = "404", description = "Procuctos no encontrados")
    public Flux<ProductoFinancieroDto> obtenerProductos(@PathVariable String codigoUnico){
        return productoService.listarPorCodigoUnico(codigoUnico);
    }

}
