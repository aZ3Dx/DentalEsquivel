package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Recepcionista;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecepcionista extends CrudRepository<Recepcionista, Integer> {
    
}
