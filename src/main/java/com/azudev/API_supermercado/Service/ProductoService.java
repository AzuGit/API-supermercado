package com.azudev.API_supermercado.Service;

import com.azudev.API_supermercado.DTO.ProductoDTO;
import com.azudev.API_supermercado.Exception.NotFoundException;
import com.azudev.API_supermercado.Mapper.Mapper;
import com.azudev.API_supermercado.Model.Producto;
import com.azudev.API_supermercado.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductoService implements IProductoService{



    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoDTO> findAll() {
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
               return productos.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO save(ProductoDTO productoDTO) {

       Producto producto = Producto.builder()
               .nombre(productoDTO.getNombre())
               .categoria(productoDTO.getCategoria())
               .precio(productoDTO.getPrecio())
               .cantidad(productoDTO.getCantidad())
               .build();
       return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public ProductoDTO update(Long id, ProductoDTO productoDTO) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado"));
        producto.setNombre(productoDTO.getNombre());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setCategoria(productoDTO.getCategoria());
        producto.setCantidad(productoDTO.getCantidad());

        return Mapper.toDTO(productoRepository.save(producto));
    }

    @Override
    public void delete(Long id) {

        if (!productoRepository.existsById(id)) {

            throw new NotFoundException("Producto no encontrado para eliminar");
        }

        productoRepository.deleteById(id);
    }
}
