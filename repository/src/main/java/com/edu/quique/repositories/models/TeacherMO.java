package com.edu.quique.repositories.models;

import javax.persistence.*;

import com.edu.quique.repositories.BaseMO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "teachers")
public class TeacherMO extends BaseMO implements Serializable {

  @Id
  @Column(name = "teacher_id", length = 9)
  private String teacherId;

  @Column(name = "email")
  private String email;

  @Column(name = "name")
  private String name;

  @Column(name = "first_surname")
  private String firstSurname;

  @Column(name = "second_surname")
  private String secondSurname;

  @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
  private List<TeachingHourMO> teachingHours;
}
