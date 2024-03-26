package com.edu.quique.application.ports.in.usecases;

import com.edu.quique.application.domain.Teacher;

public interface GetTeacherByEmailUseCasePort {
    Teacher execute(String email);
}
