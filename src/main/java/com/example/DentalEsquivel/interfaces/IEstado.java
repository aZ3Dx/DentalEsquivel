package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstado extends CrudRepository<Estado, Integer> {
    
}
