package com.edu.quique.application.domain;

import com.edu.quique.application.utils.TimeInterval;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Absence implements Comparable<Absence> {
  private Long absenceId;
  private String dayOfWeek;
  private LocalDate absenceDate;
  private TimeInterval timeInterval;
  private Teacher absentTeacher;
  private TimetableGroup timetableGroup;
  private Boolean isAssigned;
  private LocalDateTime assignedTime;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if ((o instanceof Absence absence)) {
      return this.absenceId.equals(absence.getAbsenceId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.absenceId.hashCode();
  }

  @Override
  public int compareTo(Absence o) {
    return this.absenceId.compareTo(o.getAbsenceId());
  }
}