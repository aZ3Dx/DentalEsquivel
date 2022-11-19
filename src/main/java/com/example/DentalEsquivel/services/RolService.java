package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Rol;
import com.example.DentalEsquivel.interfaces.IRol;
import com.example.DentalEsquivel.interfaceservice.IRolService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService implements IRolService{
    
    @Autowired
    private IRol irol;

    @Override
    public List<Rol> Listar() {
        return (List<Rol>) irol.findAll();
    }

    @Override
    public Rol ListarId(int id) {
        return irol.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Rol rol) {
        irol.save(rol);
    }

    @Override
    public void Eliminar(int id) {
        irol.deleteById(id);
    }
    
}
