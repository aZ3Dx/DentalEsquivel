package com.example.DentalEsquivel.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_usuario;
    
    @Column(nullable=false, length=20)
    @NotEmpty
    @Size(min = 3,max = 20)
    private String username;
    
    @Column(nullable=false, length=60)
    @NotEmpty
    @Size(min = 6,max = 60)
    private String password;
    
    @Column(nullable=false)
    private Boolean activo;
    
    @ManyToOne
    @JoinColumn(name="idrol", referencedColumnName = "id_rol")
    @NotNull
    private Rol idrol;
}
