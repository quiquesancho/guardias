package com.edu.quique.repositories.models;

import java.time.LocalTime;

import javax.persistence.*;

import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.repositories.BaseMO;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="teaching_hours")
public class TeachingHoursMO extends BaseMO {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "teaching_hours_id")
    private Long teachingHoursId;

    private LocalTime startHour;

    private LocalTime endHour;

    private DaysOfWeek dayOfWeek;

    @ManyToOne
    private TeacherMO docente;

    private String ocupacion;
    
}