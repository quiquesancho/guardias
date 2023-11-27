package com.edu.quique.repositories.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.edu.quique.repositories.BaseMO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Horariogrupos")
@Getter
@Setter
@NoArgsConstructor
public class HorarioGrupoMO extends BaseMO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String diaSemana;
    
    private Date desde;

    private Date hasta;

    private String grupo;

    private String aula;
    
    private String contenido;

    @ManyToOne
    private TeacherMO docente;
    
}