package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Doctor;
import com.example.DentalEsquivel.interfaces.IDoctor;
import com.example.DentalEsquivel.interfaceservice.IDoctorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService implements IDoctorService{
    
    @Autowired
    private IDoctor idoctor;

    @Override
    public List<Doctor> Listar() {
        return (List<Doctor>) idoctor.findAll();
    }

    @Override
    public Doctor ListarId(int id) {
        return idoctor.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Doctor doctor) {
        idoctor.save(doctor);
    }

    @Override
    public void Eliminar(int id) {
        idoctor.deleteById(id);
    }

    @Override
    public List<Doctor> ListaActiveDoctors() {
        return idoctor.ListActiveDoctors();
    }
    
}
