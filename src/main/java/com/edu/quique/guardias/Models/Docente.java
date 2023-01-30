package com.edu.quique.guardias.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.edu.quique.guardias.Entity.AbstractEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="Docentes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Docente {
    
    @Id
    @Column(length = 9)
    private String dni;

    private String email;
   
    private String nombre;
  
    private String primapel;

    private String segapel;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "esta_guardia")
    private Long isGuardia;

    @PrePersist
    private void beforeSave(){
        this.createdAt = new Date();
        this.isGuardia = AbstractEntity.FALSO_LONG;
    }

    public boolean equals(Object o) {

        Docente d;
        if(this == o){
            return true;
        } else if (!(o instanceof Docente)){
            return false;
        } else {
            d = (Docente) o;
            return ((this.dni != null) && (this.dni == d.getDni()));
        }
    }
    
}