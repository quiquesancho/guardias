package com.edu.quique.repositories.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "absence")
public class AbsenceMO implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "absence_id")
  private Long absenceId;

  @Column(name = "day_of_week")
  private String dayOfWeek;

  @Column(name = "absence_date")
  private LocalDate absenceDate;

  @Column(name = "start_hour")
  private LocalTime startHour;

  @Column(name = "end_hour")
  private LocalTime endHour;

  @ManyToOne
  @JoinColumn(name = "teacher_id")
  private TeacherMO absentTeacher;

  @Column(name = "is_assigned")
  private Boolean isAssigned;
}