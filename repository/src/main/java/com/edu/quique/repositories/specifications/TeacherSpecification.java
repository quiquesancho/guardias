package com.edu.quique.repositories.specifications;

import com.edu.quique.application.domain.queryparams.TeacherQueryParams;
import com.edu.quique.repositories.models.TeacherMO;
import org.springframework.data.jpa.domain.Specification;

public interface TeacherSpecification {
    Specification<TeacherMO> getSpecification(TeacherQueryParams params);
}
