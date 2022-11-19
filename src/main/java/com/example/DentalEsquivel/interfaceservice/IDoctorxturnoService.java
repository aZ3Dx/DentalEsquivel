package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Doctorxturno;
import java.sql.Time;
import java.util.List;

public interface IDoctorxturnoService {
    public List<Doctorxturno> Listar();
    public List<Doctorxturno> ListaTurnosDocActivos();
    public List<Doctorxturno> ListHorasPorDia(Character dia);
    public Doctorxturno ListarId(int id);
    public Doctorxturno DoctorPorTurno(Character dia, Time hora);
    public void Guardar(Doctorxturno doctorxturno);
    public void Eliminar(int id);
}
