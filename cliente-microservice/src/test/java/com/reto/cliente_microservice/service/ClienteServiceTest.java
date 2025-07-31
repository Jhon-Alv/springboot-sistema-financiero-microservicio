package com.reto.cliente_microservice.service;

import com.reto.cliente_microservice.dto.ClienteDto;
import com.reto.cliente_microservice.model.Cliente;
import com.reto.cliente_microservice.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void obtenerClientePorCodigoUnico_deberiaRetornarClienteDto() {

        String codigoUnico = "TEST1234";

        Cliente cliente = Cliente.builder()
                .codigoUnico(codigoUnico)
                .nombres("Test Nombre")
                .apellidos("Test Apellido")
                .tipoDocumento("DNI")
                .numeroDocumento("12345678")
                .build();

        Mockito.when(clienteRepository.findByCodigoUnico(codigoUnico))
                .thenReturn(Mono.just(cliente));

        Mono<ClienteDto> resultado = clienteService.obtenerClientePorCodigoUnico(codigoUnico);

        StepVerifier.create(resultado)
                .expectNextMatches(clienteDto ->
                        clienteDto.getNombres().equals("Test Nombre") &&
                        clienteDto.getApellidos().equals("Test Apellido") &&
                        clienteDto.getTipoDocumento().equals("DNI") &&
                        clienteDto.getNumeroDocumento().equals("12345678")
                )
                .verifyComplete();

    }

}
