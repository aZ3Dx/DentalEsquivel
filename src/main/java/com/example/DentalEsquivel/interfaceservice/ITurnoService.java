package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Turno;
import java.util.List;

public interface ITurnoService {
    public List<Turno> Listar();
    public List<Turno> ListaTurnosOrderByHora();
    public Turno ListarId(int id);
    public void Guardar(Turno turno);
    public void Eliminar(int id);
}
