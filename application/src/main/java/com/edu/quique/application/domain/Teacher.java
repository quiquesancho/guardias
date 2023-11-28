package com.edu.quique.application.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Comparable<Teacher> {
  private String teacherId;
  private String email;
  private String name;
  private String firstSurname;
  private String secondSurname;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if ((o instanceof Teacher teacher)) {
      return this.teacherId.equals(teacher.getTeacherId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.teacherId.hashCode();
  }

  @Override
  public int compareTo(Teacher o) {
    return this.teacherId.compareTo(o.getTeacherId());
  }
}