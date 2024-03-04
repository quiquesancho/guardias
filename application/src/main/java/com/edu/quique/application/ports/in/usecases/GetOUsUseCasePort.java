package com.edu.quique.application.ports.in.usecases;

import java.util.List;

public interface GetOUsUseCasePort {
    List<String> execute(String email);
}
