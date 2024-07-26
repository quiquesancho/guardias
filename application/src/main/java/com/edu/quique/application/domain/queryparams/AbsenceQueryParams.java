package com.edu.quique.application.domain.queryparams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AbsenceQueryParams {
    private String email;
    private LocalDate absenceDate;
    private LocalTime instantHour;
}
