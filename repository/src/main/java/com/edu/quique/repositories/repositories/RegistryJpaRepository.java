package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.RegistryMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistryJpaRepository extends JpaRepository<RegistryMO, Long> {}
