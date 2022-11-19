package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Rol;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRol extends CrudRepository<Rol, Integer> {
    
}
