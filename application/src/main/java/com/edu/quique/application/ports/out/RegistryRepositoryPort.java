package com.edu.quique.application.ports.out;

import com.edu.quique.application.domain.Registry;

public interface RegistryRepositoryPort {
    Registry save(Registry registry);
}
