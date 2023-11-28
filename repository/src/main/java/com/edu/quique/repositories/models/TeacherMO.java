package com.edu.quique.repositories.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import com.edu.quique.repositories.BaseMO;
import lombok.*;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="teachers")
public class TeacherMO extends BaseMO implements Serializable {
    
    @Id
    @Column(name = "teacher_id",length = 9)
    private String teacherId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "first_surname")
    private String firstSurname;

    @Column(name = "second_surname")
    private String secondSurname;

    public boolean equals(Object o) {
        if(this == o) return true;
        if ((o instanceof TeacherMO teacher)){
            return this.teacherId.equals(teacher.getTeacherId());
        }
        return false;
    }
    
}