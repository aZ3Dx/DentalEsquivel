package com.example.DentalEsquivel.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="ciudades")
public class Ciudad {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_ciudad;
    
    @Column(nullable=false, length=26)
    @NotEmpty
    @Size(min = 1,max = 26)
    private String ciudad;
}
