package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Doctor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctor extends CrudRepository<Doctor, Integer> {
    @Query(value = "SELECT * FROM doctores D JOIN personas P ON P.id_persona = D.idpersona JOIN usuarios U ON U.id_usuario = P.idusuario WHERE U.activo = true",
            nativeQuery = true)
    List<Doctor> ListActiveDoctors();
}
