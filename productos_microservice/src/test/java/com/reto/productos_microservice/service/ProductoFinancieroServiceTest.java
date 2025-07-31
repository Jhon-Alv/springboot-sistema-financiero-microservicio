package com.reto.productos_microservice.service;

import com.reto.productos_microservice.dto.ProductoFinancieroDto;
import com.reto.productos_microservice.model.ProductoFinanciero;
import com.reto.productos_microservice.repository.ProductoFinancieroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProductoFinancieroServiceTest {

    @Mock
    private ProductoFinancieroRepository productoRepository;

    @InjectMocks
    private ProductoFinancieroService productoService;

    @Test
    void listarPorCodigoUnico(){

        String codigoUnico = "TEST1234";

        ProductoFinanciero producto1 = ProductoFinanciero.builder()
                .id("1")
                .codigoUnico(codigoUnico)
                .tipoProducto("Cuenta de Ahorros")
                .nombreProducto("Cuenta Joven")
                .saldo(1500.0)
                .build();

        ProductoFinanciero producto2 = ProductoFinanciero.builder()
                .id("2")
                .codigoUnico(codigoUnico)
                .tipoProducto("Tarjeta de Crédito")
                .nombreProducto("Visa Clásica")
                .saldo(2300.0)
                .build();

        when(productoRepository.findAllByCodigoUnico(codigoUnico))
                .thenReturn(Flux.fromIterable(List.of(producto1, producto2)));

        Flux<ProductoFinancieroDto> resultado = productoService.listarPorCodigoUnico(codigoUnico);

        StepVerifier.create(resultado)
                .expectNextMatches(dto -> dto.getNombreProducto().equals("Cuenta Joven"))
                .expectNextMatches(dto -> dto.getNombreProducto().equals("Visa Clásica"))
                .verifyComplete();

    }

}
