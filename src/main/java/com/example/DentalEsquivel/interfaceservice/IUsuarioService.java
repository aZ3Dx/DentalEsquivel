package com.example.DentalEsquivel.interfaceservice;

import com.example.DentalEsquivel.entitys.Usuario;
import java.util.List;

public interface IUsuarioService {
    public List<Usuario> Listar();
    public Usuario ListarId(int id);
    public void Guardar(Usuario usuario);
    public void Eliminar(int id);
}
