package com.edu.quique.repositories.repositories;


import com.edu.quique.repositories.models.TeachingHoursMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioDocenteRepository extends JpaRepository<TeachingHoursMO, Long> {

}