package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.AbsenceMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceJpaRepository extends JpaRepository<AbsenceMO, Long> {}
