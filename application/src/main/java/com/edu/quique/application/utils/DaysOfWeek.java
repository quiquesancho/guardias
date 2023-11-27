package com.edu.quique.application.utils;

import lombok.Getter;

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

}
