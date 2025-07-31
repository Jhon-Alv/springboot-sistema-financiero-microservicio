package com.reto.cliente_microservice.service;

import com.reto.cliente_microservice.dto.ClienteDto;
import com.reto.cliente_microservice.mapper.ClienteMapper;
import com.reto.cliente_microservice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Mono<ClienteDto> obtenerClientePorCodigoUnico(String codigoUnico) {
        return clienteRepository.findByCodigoUnico(codigoUnico)
                .map(ClienteMapper::toDto);
    }
}

