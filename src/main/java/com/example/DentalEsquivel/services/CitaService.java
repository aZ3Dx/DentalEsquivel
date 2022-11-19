package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Cita;
import com.example.DentalEsquivel.interfaces.ICita;
import com.example.DentalEsquivel.interfaceservice.ICitaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitaService implements ICitaService{
    
    @Autowired
    private ICita icita;

    @Override
    public List<Cita> Listar() {
        return (List<Cita>) icita.findAll();
    }

    @Override
    public Cita ListarId(int id) {
        return icita.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Cita cita) {
        icita.save(cita);
    }

    @Override
    public void Eliminar(int id) {
        icita.deleteById(id);
    }

    @Override
    public List<Cita> ListaCloseCitas() {
        return icita.ListCloseCitas();
    }

    @Override
    public List<Cita> ListaCloseCitasPerStates(String state) {
        return icita.ListCloseCitasPerStates(state);
    }

    @Override
    public List<Cita> ListaCloseCitasInOrder() {
        return icita.ListCloseCitasInOrder();
    }
    
}
