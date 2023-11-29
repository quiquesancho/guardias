package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.TimetableGroup;

public interface TimetableGroupServicePort {
    TimetableGroup save(TimetableGroup timetableGroup);
    void deleteAll();
}