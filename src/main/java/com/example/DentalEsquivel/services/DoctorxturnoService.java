package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Doctorxturno;
import com.example.DentalEsquivel.interfaces.IDoctorxturno;
import com.example.DentalEsquivel.interfaceservice.IDoctorxturnoService;
import java.sql.Time;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorxturnoService implements IDoctorxturnoService{
    
    @Autowired
    private IDoctorxturno idoctorxturno;

    @Override
    public List<Doctorxturno> Listar() {
        return (List<Doctorxturno>) idoctorxturno.findAll();
    }

    @Override
    public Doctorxturno ListarId(int id) {
        return idoctorxturno.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Doctorxturno doctorxturno) {
        idoctorxturno.save(doctorxturno);
    }

    @Override
    public void Eliminar(int id) {
        idoctorxturno.deleteById(id);
    }

    @Override
    public List<Doctorxturno> ListaTurnosDocActivos() {
        return idoctorxturno.ListTurnosDocActivos();
    }

    @Override
    public List<Doctorxturno> ListHorasPorDia(Character dia) {
        return idoctorxturno.ListHorasPorDia(dia);
    }

    @Override
    public Doctorxturno DoctorPorTurno(Character dia, Time hora) {
        return idoctorxturno.DocPorTurno(dia, hora);
    }
    
}
