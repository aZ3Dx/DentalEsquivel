package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Ciudad;
import com.example.DentalEsquivel.interfaces.ICiudad;
import com.example.DentalEsquivel.interfaceservice.ICiudadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CiudadService implements ICiudadService{
    
    @Autowired
    private ICiudad iciudad;

    @Override
    public List<Ciudad> Listar() {
        return (List<Ciudad>) iciudad.findAll();
    }

    @Override
    public Ciudad ListarId(int id) {
        return iciudad.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Ciudad ciudad) {
        iciudad.save(ciudad);
    }

    @Override
    public void Eliminar(int id) {
        iciudad.deleteById(id);
    }

    /*@Override
    public Ciudad ListarId(String ciudad) {
        iciudad.
    }*/
    
}
