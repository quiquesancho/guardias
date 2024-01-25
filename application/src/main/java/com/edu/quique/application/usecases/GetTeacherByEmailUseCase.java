package com.edu.quique.application.usecases;

import com.edu.quique.application.domain.Teacher;
import com.edu.quique.application.ports.in.usecases.GetTeacherByEmailUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetTeacherByEmailUseCase implements GetTeacherByEmailUseCasePort {
    @Override
    public Teacher execute(String email) {
        return null;
    }
}
