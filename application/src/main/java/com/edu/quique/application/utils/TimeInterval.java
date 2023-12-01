package com.edu.quique.application.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static com.edu.quique.application.utils.AppConstants.INTERVAL_MINUTES;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TimeInterval {
  private LocalTime startHour;
  private LocalTime endHour;

  public static List<TimeInterval> generateIntervals(TimeInterval timeInterval) {
    List<TimeInterval> timeIntervalList = new ArrayList<>();

    LocalTime startDate = timeInterval.getStartHour();
    LocalTime endDate = timeInterval.getEndHour();

    while (startDate.isBefore(endDate)) {
      TimeInterval interval =
          TimeInterval.builder()
              .startHour(startDate)
              .endHour(startDate.plusMinutes(INTERVAL_MINUTES))
              .build();

      timeIntervalList.add(interval);

      startDate = interval.getEndHour();
    }

    return timeIntervalList;
  }
}
