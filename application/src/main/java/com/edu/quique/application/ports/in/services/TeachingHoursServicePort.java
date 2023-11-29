package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.TeachingHours;

public interface TeachingHoursServicePort {
    TeachingHours save(TeachingHours teachingHours);
    void deleteAll();
}
