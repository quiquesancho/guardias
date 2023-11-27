package com.edu.quique.repositories.repositories;

import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<TeacherMO, String> {
    
}