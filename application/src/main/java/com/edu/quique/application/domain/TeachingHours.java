package com.edu.quique.application.domain;

import com.edu.quique.application.utils.DaysOfWeek;
import java.time.LocalTime;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeachingHours {
    private Long teachingHoursId;
    private LocalTime startHour;
    private LocalTime endHour;
    private DaysOfWeek dayOfWeek;
    private Teacher teacher;
    private String occupation;
    
}