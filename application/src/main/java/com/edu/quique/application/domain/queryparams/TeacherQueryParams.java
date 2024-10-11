package com.edu.quique.application.domain.queryparams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherQueryParams {
    private String teacherId;
    private String email;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private String dayOfWeek;
    private LocalTime instantNow;
    private String occupation;
}
