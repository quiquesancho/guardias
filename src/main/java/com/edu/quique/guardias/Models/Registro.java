package com.edu.quique.guardias.Models;

import java.util.Date;

import javax.persistence.Column;
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
@Table(name = "Registros_ausencias")
@Getter
@Setter
@NoArgsConstructor
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private Date hora;

    private String grupo;

    private String aula;

    @Column(columnDefinition="TEXT")
    private String observaciones;

    @ManyToOne
    private Docente docenteGuardia;

    @ManyToOne
    private Docente docenteAusente;
    
}