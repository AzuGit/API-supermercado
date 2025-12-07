package com.azudev.API_supermercado.Service;

import com.azudev.API_supermercado.DTO.SucursalDTO;
import com.azudev.API_supermercado.Exception.NotFoundException;
import com.azudev.API_supermercado.Mapper.Mapper;
import com.azudev.API_supermercado.Model.Sucursal;
import com.azudev.API_supermercado.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SucursalService implements ISucursalService{

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public List<SucursalDTO> findAll() {
        List<Sucursal> sucursalList= (List<Sucursal>) sucursalRepository.findAll();
        return sucursalList.stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO save(SucursalDTO sucursalDTO) {

        Sucursal sucursal = Sucursal.builder()
                .nombre(sucursalDTO.getNombre())
                .direccion(sucursalDTO.getDireccion())
                .build();

        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public SucursalDTO update(Long id, SucursalDTO sucursalDTO) {

        Sucursal sucursal = sucursalRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("sucursal no encontrada"));

        sucursal.setNombre(sucursalDTO.getNombre());
        sucursal.setDireccion(sucursalDTO.setDireccion();

        return Mapper.toDTO(sucursalRepository.save(sucursal));
    }

    @Override
    public void delete(Long id) {
        if (!sucursalRepository.existsById(id)){
            throw new NotFoundException("Sucursal no encotrada para actualizar");
        }

        sucursalRepository.deleteById(id);

    }
}
