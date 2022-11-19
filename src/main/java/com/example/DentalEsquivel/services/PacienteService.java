package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Paciente;
import com.example.DentalEsquivel.interfaces.IPaciente;
import com.example.DentalEsquivel.interfaceservice.IPacienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteService implements IPacienteService{
    
    @Autowired
    private IPaciente ipaciente;

    @Override
    public List<Paciente> Listar() {
        return (List<Paciente>) ipaciente.findAll();
    }

    @Override
    public Paciente ListarId(int id) {
        return ipaciente.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Paciente paciente) {
        ipaciente.save(paciente);
    }

    @Override
    public void Eliminar(int id) {
        ipaciente.deleteById(id);
    }
    
}
