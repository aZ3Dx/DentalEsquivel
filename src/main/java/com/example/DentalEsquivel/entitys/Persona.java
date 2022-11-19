package com.example.DentalEsquivel.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Entity
@Table(name="personas")
public class Persona {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_persona;
    
    @Column(nullable=false, length=30)
    @NotEmpty
    @Size(min = 1,max = 30)
    private String nombres;
    
    @Column(nullable=false, length=30)
    @NotEmpty
    @Size(min = 1,max = 30)
    private String apellidos;
    
    @Column(nullable=false, length=9)
    @NotEmpty
    @Size(min = 9,max = 9)
    private String celular;
    
    @Column(nullable=false, length=50)
    @NotEmpty
    @Size(min = 3,max = 50)
    private String direccion;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable=false)
    @NotNull
    private java.util.Date fechanac;
    
    @Column(nullable=false, length=8)
    @NotEmpty
    @Size(min = 8,max = 8)
    private String dni;
    
    @Column(nullable=false, length = 1)
    @NotEmpty
    private String genero;
    
    @OneToOne
    @JoinColumn(name="idusuario", referencedColumnName = "id_usuario")
    private Usuario idusuario;
    
    @ManyToOne
    @JoinColumn(name="idciudad", referencedColumnName = "id_ciudad")
    @NotNull
    private Ciudad idciudad;
}
