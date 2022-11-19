package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Paciente;
import java.util.List;

public interface IPacienteService {
    public List<Paciente> Listar();
    public Paciente ListarId(int id);
    public void Guardar(Paciente paciente);
    public void Eliminar(int id);
}
