package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Cita;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICita extends CrudRepository<Cita, Integer> {
    @Query(value = "SELECT * FROM citas C JOIN estados E ON E.id_estado = C.idestado WHERE E.id_estado > 1 and E.id_estado < 4 ORDER BY C.fecha asc, C.hora asc",
            nativeQuery = true)
    List<Cita> ListCloseCitas();
    @Query(value = "SELECT * FROM citas C JOIN estados E ON E.id_estado = C.idestado WHERE E.estado = ?1 ORDER BY C.fecha asc, C.hora asc",
            nativeQuery = true)
    List<Cita> ListCloseCitasPerStates(String state);
    @Query(value = "SELECT * FROM citas C ORDER BY C.fecha asc, C.hora asc",
            nativeQuery = true)
    List<Cita> ListCloseCitasInOrder();
}
