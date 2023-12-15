package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.TeachingHour;

import java.util.List;

public interface TeachingHoursRepositoryPort {
    List<TeachingHour> findAllTeachingHoursGuard();
    TeachingHour save(TeachingHour teachingHour);
    void deleteAll();
}
