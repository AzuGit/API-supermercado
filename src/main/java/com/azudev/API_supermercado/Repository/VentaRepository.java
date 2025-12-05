package com.azudev.API_supermercado.Repository;

import com.azudev.API_supermercado.Model.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VentaRepository extends CrudRepository<Venta, Long> {
}
