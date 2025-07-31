package com.reto.cliente_microservice.repository;

import com.reto.cliente_microservice.model.Cliente;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface ClienteRepository extends ReactiveMongoRepository<Cliente, String> {
    Mono<Cliente> findByCodigoUnico(String codigoUnico);
}
