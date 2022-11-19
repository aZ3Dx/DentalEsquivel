package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Cita;
import java.util.List;

public interface ICitaService {
    public List<Cita> Listar();
    public List<Cita> ListaCloseCitas();
    public List<Cita> ListaCloseCitasPerStates(String state);
    public List<Cita> ListaCloseCitasInOrder();
    public Cita ListarId(int id);
    public void Guardar(Cita cita);
    public void Eliminar(int id);
}
