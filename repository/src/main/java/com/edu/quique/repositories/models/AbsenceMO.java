package com.edu.quique.repositories.models;

import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.repositories.BaseMO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "absence_teacher")
public class AbsenceMO extends BaseMO {

    @Id
    @Column(name = "absence_teacher_id")
    private Long id;

    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    private DaysOfWeek dayOfWeek;

    @Column(name = "absence_date")
    private LocalDate absenceDate;

    @Column(name = "start_hour")
    private LocalTime startHour;

    @Column(name = "end_hour")
    private LocalTime endHour;

    @ManyToOne
    @JoinColumn(name = "absent_teacher")
    private TeacherMO absentTeacher;

    @Column(name = "is_assigned")
    private Long idAssigned;
}