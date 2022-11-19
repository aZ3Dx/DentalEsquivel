package com.example.DentalEsquivel.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_paciente;
    
    @Column(nullable=false, length=40)
    @Size(max = 40)
    private String alergias;
    
    @OneToOne
    @JoinColumn(name="idpersona", referencedColumnName = "id_persona")
    @NotNull
    private Persona idpersona;
}
