package com.example.DentalEsquivel.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="doctorxturno")
public class Doctorxturno {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_doctorxturno;
    
    @ManyToOne
    @JoinColumn(name="iddoctor", referencedColumnName = "id_doctor")
    @NotNull
    private Doctor iddoctor;
    
    @ManyToOne
    @JoinColumn(name="idturno", referencedColumnName = "id_turno")
    @NotNull
    private Turno idturno;
}
