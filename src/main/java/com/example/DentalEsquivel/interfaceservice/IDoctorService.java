package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Doctor;
import java.util.List;

public interface IDoctorService {
    public List<Doctor> Listar();
    public List<Doctor> ListaActiveDoctors();
    public Doctor ListarId(int id);
    public void Guardar(Doctor doctor);
    public void Eliminar(int id);
}
