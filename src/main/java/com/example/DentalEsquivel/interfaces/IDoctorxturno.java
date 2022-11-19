package com.example.DentalEsquivel.interfaces;

import com.example.DentalEsquivel.entitys.Doctorxturno;
import java.sql.Time;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorxturno extends CrudRepository<Doctorxturno, Integer> {

    @Query(value = "SELECT DXT.id_doctorxturno, DXT.iddoctor, DXT.idturno FROM doctores D JOIN personas P ON P.id_persona = D.idpersona JOIN doctorxturno DXT ON DXT.iddoctor = D.id_doctor JOIN turnos T ON T.id_turno = DXT.idturno JOIN usuarios U ON U.id_usuario = P.idusuario WHERE U.activo = true ORDER BY T.hora asc, T.dia asc",
            nativeQuery = true)
    List<Doctorxturno> ListTurnosDocActivos();

    @Query(value = "SELECT * FROM doctorxturno DXT JOIN turnos T ON T.id_turno = DXT.idturno WHERE T.dia = ?1",
            nativeQuery = true)
    List<Doctorxturno> ListHorasPorDia(Character dia);
    @Query(value = "SELECT * FROM doctorxturno DXT JOIN turnos T ON T.id_turno = DXT.idturno JOIN doctores D on D.id_doctor = DXT.iddoctor JOIN personas P on P.id_persona = D.idpersona WHERE T.dia = ?1 AND T.hora = ?2",
            nativeQuery = true)
    Doctorxturno DocPorTurno(Character dia, Time hora);
}
