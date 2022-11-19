package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Integer> {

}
