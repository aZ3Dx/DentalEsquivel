package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersona extends CrudRepository<Persona, Integer> {

    @Query(value = "SELECT * FROM personas P LEFT JOIN usuarios U ON U.id_usuario = P.idusuario LEFT JOIN roles R ON R.id_rol = u.idrol WHERE r.rol != 'ROLE_Paciente' or r.rol is null",
            nativeQuery = true)
    List<Persona> ListOnlyWorkers();
}
