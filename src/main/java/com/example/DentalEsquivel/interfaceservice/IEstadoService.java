package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Estado;
import java.util.List;

public interface IEstadoService {
    public List<Estado> Listar();
    public Estado ListarId(int id);
    public void Guardar(Estado estado);
    public void Eliminar(int id);
}
