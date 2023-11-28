package com.edu.quique.repositories.models;

import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.repositories.BaseMO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "timetable_group")
public class TimetableGroupMO extends BaseMO implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "timetable_group_id")
  private Long timeTableGroupId;

  @Column(name = "day_of_week")
  private DaysOfWeek dayOfWeek;

  @Column(name = "start_hour")
  private LocalTime startHour;

  @Column(name = "end_hour")
  private LocalTime endHour;

  @Column(name = "group_id")
  private String group;

  @Column(name = "classroom")
  private String classroom;

  @Column(name = "content")
  private String content;

  @ManyToOne
  @JoinColumn(name = "teacher_id")
  private TeacherMO teacher;
}