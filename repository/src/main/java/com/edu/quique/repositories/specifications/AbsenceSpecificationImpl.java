package com.edu.quique.repositories.specifications;

import com.edu.quique.application.domain.queryparams.AbsenceQueryParams;
import com.edu.quique.repositories.models.AbsenceMO;
import com.edu.quique.repositories.models.AbsenceMO_;
import com.edu.quique.repositories.models.TeacherMO_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AbsenceSpecificationImpl implements AbsenceSpecification {

  @Override
  public Specification<AbsenceMO> getSpecification(AbsenceQueryParams params) {
    List<Specification<AbsenceMO>> specs = new ArrayList<>();
    if (params.getAbsenceDate() != null) {
      specs.add(getAbsenceDate(params.getAbsenceDate()));
    }
    if (params.getEmail() != null) {
      specs.add(getEmail(params.getEmail()));
    }
    if (params.getInstantHour() != null) {
      specs.add(getStartHour(params.getInstantHour()));
      specs.add(getEndHour(params.getInstantHour()));
    }
    return specs.stream().reduce(Specification::and).orElse(null);
  }

  private Specification<AbsenceMO> getEndHour(LocalTime instantHour) {
    return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(AbsenceMO_.endHour), instantHour);
  }

  private Specification<AbsenceMO> getStartHour(LocalTime instantHour) {
    return (root, query, cb) ->
        cb.lessThanOrEqualTo(root.get(AbsenceMO_.startHour), instantHour);
  }

  private Specification<AbsenceMO> getEmail(String email) {
    return (root, query, cb) ->
        cb.equal(root.get(AbsenceMO_.absentTeacher).get(TeacherMO_.email), email);
  }

  private Specification<AbsenceMO> getAbsenceDate(LocalDate absenceDate) {
    return (root, query, cb) -> cb.equal(root.get(AbsenceMO_.absenceDate), absenceDate);
  }
}
