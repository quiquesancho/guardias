package com.edu.quique.repositories.models;

import java.io.Serializable;
import java.time.LocalTime;

import javax.persistence.*;

import com.edu.quique.repositories.BaseMO;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="teaching_hours")
public class TeachingHoursMO extends BaseMO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teaching_hours_id")
    private Long teachingHoursId;

    @Column(name = "start_hour")
    private LocalTime startHour;

    @Column(name = "end_hour")
    private LocalTime endHour;

    @Column(name = "day_of_week", length = 1)
    private String dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherMO teacher;

    @Column(name = "occupation")
    private String occupation;
    
}