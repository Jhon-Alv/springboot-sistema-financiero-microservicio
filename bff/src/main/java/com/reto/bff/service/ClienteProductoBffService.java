package com.reto.bff.service;

import com.reto.bff.dto.ClienteConProductosDto;
import com.reto.bff.dto.ProductoFinancieroDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClienteProductoBffService {

    @Autowired
    private WebClient webClient;

    @Value("${servicio.cliente.url}")
    private String clienteUrl;

    @Value("${servicio.producto.url}")
    private String productoUrl;

    public Mono<ClienteConProductosDto> obtenerInfoClienteConProductos(String codigoUnico) {
        return Mono.deferContextual(ctx -> {
            Mono<ClienteConProductosDto> clienteMono = webClient.get()
                    .uri(clienteUrl + "/api/clientes/{codigoUnico}", codigoUnico)
                    .retrieve()
                    .bodyToMono(ClienteConProductosDto.class)
                    .contextWrite(ctx);

            Mono<List<ProductoFinancieroDto>> productoMono = webClient.get()
                    .uri(productoUrl + "/api/productos/{codigoUnico}", codigoUnico)
                    .retrieve()
                    .bodyToFlux(ProductoFinancieroDto.class)
                    .collectList()
                    .contextWrite(ctx);

            return Mono.zip(clienteMono, productoMono)
                    .map(tuple -> {
                        ClienteConProductosDto cliente = tuple.getT1();
                        cliente.setProductos(tuple.getT2());
                        return cliente;
                    });
        });
    }


}
