package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Turno;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurno extends CrudRepository<Turno, Integer> {
    @Query(value="SELECT * FROM turnos T ORDER BY T.hora asc, T.dia asc",
            nativeQuery = true)
    List<Turno> ListTurnosOrderByHora();
}
