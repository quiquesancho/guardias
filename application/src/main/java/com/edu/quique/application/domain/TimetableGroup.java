package com.edu.quique.application.domain;

import com.edu.quique.application.utils.DaysOfWeek;
import java.time.LocalTime;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimetableGroup implements Comparable<TimetableGroup> {
  private Long timetableGroupId;
  private DaysOfWeek dayOfWeek;
  private LocalTime startHour;
  private LocalTime endHour;
  private String group;
  private String classroom;
  private String content;
  private Teacher teacher;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if ((o instanceof TimetableGroup timetableGroup)) {
      return this.timetableGroupId.equals(timetableGroup.getTimetableGroupId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.timetableGroupId.hashCode();
  }

  @Override
  public int compareTo(TimetableGroup o) {
    return this.timetableGroupId.compareTo(o.getTimetableGroupId());
  }
}