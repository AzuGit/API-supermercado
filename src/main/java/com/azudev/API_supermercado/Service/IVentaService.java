package com.azudev.API_supermercado.Service;



import com.azudev.API_supermercado.DTO.VentaDTO;

import java.util.List;
import java.util.Optional;

public interface IVentaService {

    List<VentaDTO> findAll();

    VentaDTO save(VentaDTO ventaDTO);

    VentaDTO update(Long id, VentaDTO ventaDTO);

    void delete(Long id);


}
