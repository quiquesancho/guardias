package com.edu.quique.repositories.specifications;

import com.edu.quique.application.domain.queryparams.AbsenceQueryParams;
import com.edu.quique.repositories.models.AbsenceMO;
import org.springframework.data.jpa.domain.Specification;

public interface AbsenceSpecification {
    Specification<AbsenceMO> getSpecification(AbsenceQueryParams params);
}
