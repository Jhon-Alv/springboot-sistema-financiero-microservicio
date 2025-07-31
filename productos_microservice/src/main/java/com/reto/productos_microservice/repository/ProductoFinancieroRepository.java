package com.reto.productos_microservice.repository;

import com.reto.productos_microservice.model.ProductoFinanciero;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductoFinancieroRepository extends ReactiveMongoRepository<ProductoFinanciero, String> {
    Flux<ProductoFinanciero> findAllByCodigoUnico(String codigoUnico);
}
