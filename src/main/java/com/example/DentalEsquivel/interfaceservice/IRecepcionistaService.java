package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Recepcionista;
import java.util.List;

public interface IRecepcionistaService {
    public List<Recepcionista> Listar();
    public Recepcionista ListarId(int id);
    public void Guardar(Recepcionista recepcionista);
    public void Eliminar(int id);
}
