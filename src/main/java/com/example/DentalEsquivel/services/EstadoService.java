package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Estado;
import com.example.DentalEsquivel.interfaces.IEstado;
import com.example.DentalEsquivel.interfaceservice.IEstadoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService implements IEstadoService{
    
    @Autowired
    private IEstado iestado;

    @Override
    public List<Estado> Listar() {
        return (List<Estado>) iestado.findAll();
    }

    @Override
    public Estado ListarId(int id) {
        return iestado.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Estado estado) {
        iestado.save(estado);
    }

    @Override
    public void Eliminar(int id) {
        iestado.deleteById(id);
    }
    
}
