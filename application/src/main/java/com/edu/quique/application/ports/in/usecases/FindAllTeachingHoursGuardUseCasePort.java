package com.edu.quique.application.ports.in.usecases;

import com.edu.quique.application.domain.TeachingHour;

import java.util.List;

public interface FindAllTeachingHoursGuardUseCasePort {
    List<TeachingHour> execute();
}
