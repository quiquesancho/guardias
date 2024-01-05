package com.edu.quique.repositories.models;

import com.edu.quique.repositories.BaseMO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "absence")
public class AbsenceMO extends BaseMO implements Serializable {

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

  @ManyToOne
  @JoinColumn(name = "timetable_group_id")
  private TimetableGroupMO timetableGroup;

  @Column(name = "is_assigned")
  private Boolean isAssigned;

  @Column(name = "assigned_time")
  private OffsetDateTime assignedTime;
}