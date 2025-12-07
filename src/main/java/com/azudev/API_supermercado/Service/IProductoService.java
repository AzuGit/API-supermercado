package com.azudev.API_supermercado.Service;

import com.azudev.API_supermercado.DTO.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> findAll();

    ProductoDTO save(ProductoDTO productoDTO);

    ProductoDTO update(Long id, ProductoDTO productoDTO);

    void delete(Long id);
}
