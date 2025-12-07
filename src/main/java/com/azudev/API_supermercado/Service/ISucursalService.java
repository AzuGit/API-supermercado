package com.azudev.API_supermercado.Service;

import com.azudev.API_supermercado.DTO.SucursalDTO;

import java.util.List;

public interface ISucursalService {

    List<SucursalDTO> findAll();

    SucursalDTO save(SucursalDTO sucursalDTO);

    SucursalDTO update(Long id, SucursalDTO sucursalDTO);

    void delete(Long id);
}
