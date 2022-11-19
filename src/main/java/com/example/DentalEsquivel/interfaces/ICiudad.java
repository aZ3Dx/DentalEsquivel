package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Ciudad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICiudad extends CrudRepository<Ciudad, Integer> {

}
