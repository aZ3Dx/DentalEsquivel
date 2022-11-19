package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Persona;
import com.example.DentalEsquivel.interfaces.IPersona;
import com.example.DentalEsquivel.interfaceservice.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{
    
    @Autowired
    private IPersona ipersona;

    @Override
    public List<Persona> Listar() {
        return (List<Persona>) ipersona.findAll();
    }

    @Override
    public Persona ListarId(int id) {
        return ipersona.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Persona persona) {
        ipersona.save(persona);
    }

    @Override
    public void Eliminar(int id) {
        ipersona.deleteById(id);
    }

    @Override
    public List<Persona> ListarSoloTrabajadores() {
        return ipersona.ListOnlyWorkers();
    }
    
}
