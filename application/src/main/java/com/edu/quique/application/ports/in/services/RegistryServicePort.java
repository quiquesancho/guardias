package com.edu.quique.application.ports.in.services;

import com.edu.quique.application.domain.Registry;

public interface RegistryServicePort {
    Registry save(Registry registry);
    void createRegistries();
}
