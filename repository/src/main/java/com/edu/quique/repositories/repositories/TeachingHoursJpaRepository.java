package com.edu.quique.repositories.repositories;


import com.edu.quique.repositories.models.TeachingHourMO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeachingHoursJpaRepository extends JpaRepository<TeachingHourMO, Long> {

    List<TeachingHourMO> findAllByOccupation(String occupation);
}
