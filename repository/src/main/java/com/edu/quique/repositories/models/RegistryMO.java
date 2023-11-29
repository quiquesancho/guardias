package com.edu.quique.repositories.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registry_absence")
public class RegistryMO {

    @Id
    private Long id;

    private Date fecha;

    private Date hora;

    private String grupo;

    private String aula;

    @Column(columnDefinition="TEXT")
    private String observaciones;

    @ManyToOne
    private TeacherMO docenteGuardia;

    @ManyToOne
    private TeacherMO docenteAusente;
    
}