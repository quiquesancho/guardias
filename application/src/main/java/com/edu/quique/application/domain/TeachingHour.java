package com.edu.quique.application.domain;

import com.edu.quique.application.utils.DaysOfWeek;
import com.edu.quique.application.utils.TimeInterval;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeachingHour implements Comparable<TeachingHour> {
  private Long teachingHoursId;
  private TimeInterval timeInterval;
  private String dayOfWeek;
  private Teacher teacher;
  private String occupation;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if ((o instanceof TeachingHour teachingHours)) {
      return this.teachingHoursId.equals(teachingHours.getTeachingHoursId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.teachingHoursId.hashCode();
  }

  @Override
  public int compareTo(TeachingHour o) {
    int thisDayOrder = DaysOfWeek.getByDay(this.getDayOfWeek()).getOrder();
    int otherDayOrder = DaysOfWeek.getByDay(o.getDayOfWeek()).getOrder();
    if (thisDayOrder != otherDayOrder) {
      return Integer.compare(thisDayOrder, otherDayOrder);
    }

    return this.timeInterval.getStartHour().compareTo(o.timeInterval.getStartHour());
  }
}
