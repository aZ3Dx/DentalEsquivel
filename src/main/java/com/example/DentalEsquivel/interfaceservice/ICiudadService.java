package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Ciudad;
import java.util.List;

public interface ICiudadService {
    public List<Ciudad> Listar();
    public Ciudad ListarId(int id);
    public void Guardar(Ciudad ciudad);
    public void Eliminar(int id);
}
