package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Rol;
import java.util.List;

public interface IRolService {
    public List<Rol> Listar();
    public Rol ListarId(int id);
    public void Guardar(Rol rol);
    public void Eliminar(int id);
}
