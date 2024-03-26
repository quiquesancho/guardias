package com.edu.quique.application.utils;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum DaysOfWeek {
  MONDAY("L"),
  TUESDAY("M"),
  WEDNESDAY("X"),
  THURSDAY("J"),
  FRIDAY("V");

  private final String day;

  DaysOfWeek(String day) {
    this.day = day;
  }

  public static DaysOfWeek getByDay(String day) {
    return Arrays.stream(values())
        .filter(d -> d.getDay().equals(day))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("No enum constant for day: " + day));
  }
}
