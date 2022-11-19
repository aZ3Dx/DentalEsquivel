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
@Table(name="estados")
public class Estado {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_estado;
    
    @Column(nullable=false, length=15)
    @NotEmpty
    @Size(min = 1,max = 15)
    private String estado;
}
