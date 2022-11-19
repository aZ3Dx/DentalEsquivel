package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Paciente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaciente extends CrudRepository<Paciente, Integer> {
    
}
