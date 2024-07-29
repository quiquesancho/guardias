package com.edu.quique.application.schedule;

import com.edu.quique.application.domain.RegistryAbsence;
import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.domain.queryparams.TeacherQueryParams;
import com.edu.quique.application.ports.in.services.TeacherServicePort;
import com.edu.quique.application.ports.out.RegistryAbsenceScheduleRespositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class RegistryAbsenceSchedule implements RegistryAbsenceScheduleRespositoryPort {
    private TeacherServicePort teacherServicePort;
    private ApplicationEventPublisher publisher;

    @Override
    @Scheduled(cron = "*/10 * * * * *")
    public void publish() {
        publisher.publishEvent(
                RegistryAbsence.builder()
                        .teacherGuard(Teacher.builder().email("").build())
                        .build());
    }

    private List<Teacher> getGuardTeachers() {
        var dayOfWeek = LocalDate.now().getDayOfWeek();
        var queryParams = TeacherQueryParams.builder().build();
        return teacherServicePort.findAll();
    }
}
