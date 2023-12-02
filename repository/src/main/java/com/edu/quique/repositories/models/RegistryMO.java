package com.edu.quique.repositories.models;

import java.time.OffsetDateTime;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registry")
public class RegistryMO {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "registry_id")
  private Long registryId;

  @OneToOne
  @JoinColumn(name = "absence_id")
  private AbsenceMO absence;

  @ManyToOne
  @JoinColumn(name = "timetable_group_id")
  private TimetableGroupMO timetableGroup;

  @ManyToOne
  @JoinColumn(name = "guard_teacher_id")
  private TeacherMO teacherGuard;

  @Column(name = "observation",columnDefinition = "TEXT")
  private String observation;

  @Column(name = "assigned_time")
  private OffsetDateTime assignedTime;

  @Column(name = "is_assigned")
  private Boolean isAssigned;
}
