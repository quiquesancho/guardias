package com.edu.quique.repositories.models;

import java.io.Serializable;

import javax.persistence.*;

import com.edu.quique.repositories.BaseMO;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "registry_absence")
public class RegistryAbsenceMO extends BaseMO implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "registry_absence_id")
  private Long registryAbsenceId;

  @OneToOne
  @JoinColumn(name = "absence_id")
  private AbsenceMO absence;

  @ManyToOne
  @JoinColumn(name = "guard_teacher_id")
  private TeacherMO teacherGuard;

  @Column(name = "observation",columnDefinition = "TEXT")
  private String observation;
}
