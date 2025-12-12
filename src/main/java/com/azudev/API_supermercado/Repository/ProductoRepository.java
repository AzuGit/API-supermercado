package com.azudev.API_supermercado.Repository;

import com.azudev.API_supermercado.Model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    //Buscar producto por nombre
    Optional<Producto> findByNombre(String nombre);
}
