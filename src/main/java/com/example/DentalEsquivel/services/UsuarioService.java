package com.example.DentalEsquivel.services;

import com.example.DentalEsquivel.entitys.Usuario;
import com.example.DentalEsquivel.interfaces.IUsuario;
import com.example.DentalEsquivel.interfaceservice.IUsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService{
    
    @Autowired
    private IUsuario iusuario;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) iusuario.findAll();
    }

    @Override
    public Usuario ListarId(int id) {
        return iusuario.findById(id).orElse(null);
    }

    @Override
    public void Guardar(Usuario usuario) {
        iusuario.save(usuario);
    }

    @Override
    public void Eliminar(int id) {
        iusuario.deleteById(id);
    }
    
}
