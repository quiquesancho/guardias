package com.edu.quique.application.service;

import com.edu.quique.application.domain.TeachingHours;
import com.edu.quique.application.ports.in.services.TeachingHoursServicePort;
import com.edu.quique.application.ports.out.TeachingHoursPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Transactional
public class TeachingHoursService implements TeachingHoursServicePort {
    private TeachingHoursPort teachingHoursPort;
    @Override
    public TeachingHours save(TeachingHours teachingHours) {
        return teachingHoursPort.save(teachingHours);
    }

    @Override
    public void deleteAll() {
    teachingHoursPort.deleteAll();
    }
}