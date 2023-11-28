package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.TeachingHours;

public interface TeachingHoursPort {
    TeachingHours save(TeachingHours teachingHours);
    void deleteAll();
}
