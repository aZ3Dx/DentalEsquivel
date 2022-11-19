package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Persona;
import java.util.List;

public interface IPersonaService {
    public List<Persona> Listar();
    public List<Persona> ListarSoloTrabajadores();
    public Persona ListarId(int id);
    public void Guardar(Persona persona);
    public void Eliminar(int id);
}
