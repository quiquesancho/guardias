package com.edu.quique.guardias.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.edu.quique.guardias.Entity.AbstractEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ausencias_docentes")
@Getter
@Setter
@NoArgsConstructor
public class Ausencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fecha;

    private Date hora;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    private Docente docente;  

    private Long asignada;
    
    @PrePersist
    private void persistir(){
        this.createdAt = new Date();
        this.asignada = AbstractEntity.FALSO_LONG;
    }
    
}