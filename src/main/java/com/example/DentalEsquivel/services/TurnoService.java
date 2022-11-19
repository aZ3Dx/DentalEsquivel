package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Turno;
import com.example.DentalEsquivel.interfaces.ITurno;
import com.example.DentalEsquivel.interfaceservice.ITurnoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurnoService implements ITurnoService{
    
    @Autowired
    private ITurno iturno;

    @Override
    public List<Turno> Listar() {
        return (List<Turno>) iturno.findAll();
    }

    @Override
    public Turno ListarId(int id) {
        return iturno.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Turno turno) {
        iturno.save(turno);
    }

    @Override
    public void Eliminar(int id) {
        iturno.deleteById(id);
    }

    @Override
    public List<Turno> ListaTurnosOrderByHora() {
        return iturno.ListTurnosOrderByHora();
    }
    
}
