package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Recepcionista;
import com.example.DentalEsquivel.interfaces.IRecepcionista;
import com.example.DentalEsquivel.interfaceservice.IRecepcionistaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecepcionistaService implements IRecepcionistaService{
    
    @Autowired
    private IRecepcionista irecepcionista;

    @Override
    public List<Recepcionista> Listar() {
        return (List<Recepcionista>) irecepcionista.findAll();
    }

    @Override
    public Recepcionista ListarId(int id) {
        return irecepcionista.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Recepcionista recepcionista) {
        irecepcionista.save(recepcionista);
    }

    @Override
    public void Eliminar(int id) {
        irecepcionista.deleteById(id);
    }
    
}
