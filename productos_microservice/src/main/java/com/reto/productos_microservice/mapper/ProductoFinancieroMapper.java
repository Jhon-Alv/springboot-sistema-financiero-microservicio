package com.reto.productos_microservice.mapper;

import com.reto.productos_microservice.dto.ProductoFinancieroDto;
import com.reto.productos_microservice.model.ProductoFinanciero;

public class ProductoFinancieroMapper {

    public static ProductoFinancieroDto toDto(ProductoFinanciero productoFinanciero){
        return ProductoFinancieroDto.builder()
                .tipoProducto(productoFinanciero.getTipoProducto())
                .nombreProducto(productoFinanciero.getNombreProducto())
                .saldo(productoFinanciero.getSaldo())
                .build();
    }

}
