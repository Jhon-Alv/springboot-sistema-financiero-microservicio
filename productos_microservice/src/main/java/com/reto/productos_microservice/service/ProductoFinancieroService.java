package com.reto.productos_microservice.service;

import com.reto.productos_microservice.dto.ProductoFinancieroDto;
import com.reto.productos_microservice.mapper.ProductoFinancieroMapper;
import com.reto.productos_microservice.model.ProductoFinanciero;
import com.reto.productos_microservice.repository.ProductoFinancieroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductoFinancieroService {

    @Autowired
    private ProductoFinancieroRepository productoRepository;

    public Flux<ProductoFinancieroDto> listarPorCodigoUnico(String codigoUnico){
        return productoRepository.findAllByCodigoUnico(codigoUnico)
                .map(ProductoFinancieroMapper::toDto);
    }

}
