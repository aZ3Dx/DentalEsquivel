package com.example.DentalEsquivel.entitys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="citas")
public class Cita {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id_cita;
    
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH:mm")
    @Column(nullable=false)
    @NotNull
    private java.util.Date hora;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(nullable=false)
    @NotNull
    private java.util.Date fecha;
    
    @Column(nullable=false, length=50)
    @Size(min = 1,max = 50)
    @NotEmpty
    private String comentario;
    
    @ManyToOne
    @JoinColumn(name="idpaciente", referencedColumnName = "id_paciente")
    @NotNull
    private Paciente idpaciente;
    
    @ManyToOne
    @JoinColumn(name="iddoctor", referencedColumnName = "id_doctor")
    @NotNull
    private Doctor iddoctor;
            
    @ManyToOne
    @JoinColumn(name="idestado", referencedColumnName = "id_estado")
    private Estado idestado;
            
    @ManyToOne  
    @JoinColumn(name="idrecepcionista", referencedColumnName = "id_recepcionista")
    private Recepcionista idrecepcionista;
    
}
