package com.edu.quique.application.utils;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DaysOfWeek {
  MONDAY("L", 0),
  TUESDAY("M", 1),
  WEDNESDAY("X", 2),
  THURSDAY("J", 3),
  FRIDAY("V", 4);

  private final String day;
  private final int order;

  DaysOfWeek(String day, int order) {
    this.day = day;
    this. order = order;
  }

  public static DaysOfWeek getByDay(String day) {
    return Arrays.stream(values())
        .filter(d -> d.getDay().equals(day))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No enum constant for day: " + day));
  }

  public static DaysOfWeek getByOrder(int order) {
    return Arrays.stream(values())
            .filter(d -> d.getOrder() == order)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No enum constant for order: " + order));
  }
}
