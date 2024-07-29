package com.edu.quique.repositories.specifications;

import com.edu.quique.application.domain.queryparams.TeacherQueryParams;
import com.edu.quique.repositories.models.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherSpecificationImpl implements TeacherSpecification {
  @Override
  public Specification<TeacherMO> getSpecification(TeacherQueryParams params) {
    List<Specification<TeacherMO>> specs = new ArrayList<>();
    if (params.getTeacherId() != null) {
      specs.add(getTeacherId(params.getTeacherId()));
    }
    if (params.getEmail() != null) {
      specs.add(getEmail(params.getEmail()));
    }
    if (params.getName() != null) {
      specs.add(getName(params.getName()));
    }
    if (params.getDayOfWeek() != null) {
      specs.add(getDayOfWeek(params.getDayOfWeek()));
    }
    return specs.stream().reduce(Specification::and).orElse(null);
  }

  private Specification<TeacherMO> getTeacherId(String teacherId) {
    return (root, query, cb) -> cb.equal(root.get(TeacherMO_.teacherId), teacherId);
  }

  private Specification<TeacherMO> getEmail(String email) {
    return (root, query, cb) -> cb.equal(root.get(TeacherMO_.email), email);
  }

  private Specification<TeacherMO> getName(String name) {
    return (root, query, cb) -> cb.equal(root.get(TeacherMO_.name), name);
  }

  private Specification<TeacherMO> getDayOfWeek(String dayOfWeek) {
    return (root, query, cb) ->
        cb.equal(root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.dayOfWeek), dayOfWeek);
  }

  private Specification<TeacherMO> getStartHour(LocalTime startHour) {
    return (root, query, cb) ->
        cb.greaterThanOrEqualTo(
            root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.startHour), startHour);
  }

  private Specification<TeacherMO> getEndHour(LocalTime endHour) {
    return (root, query, cb) ->
        cb.lessThanOrEqualTo(
            root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.endHour), endHour);
  }
}
