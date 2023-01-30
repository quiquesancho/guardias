package com.edu.quique.guardias.Models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Horariodocentes")
@Getter
@Setter
@NoArgsConstructor
public class Horariodocente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Date desde;

    private Date hasta;

    private String diaSemana;

    @ManyToOne
    private Docente docente;

    private String ocupacion;
    
}