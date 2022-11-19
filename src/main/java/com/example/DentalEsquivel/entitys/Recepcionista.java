package com.example.DentalEsquivel.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="recepcionistas")
public class Recepcionista {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_recepcionista;
    
    @OneToOne
    @JoinColumn(name="idpersona", referencedColumnName = "id_persona")
    @NotNull
    private Persona idpersona;
}
