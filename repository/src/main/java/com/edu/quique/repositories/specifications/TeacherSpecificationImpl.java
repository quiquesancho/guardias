package com.edu.quique.repositories.specifications;

import com.edu.quique.application.domain.queryparams.TeacherQueryParams;
import com.edu.quique.repositories.models.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
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
    specs.add(getTeachingHoursSpecifications(params));
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

  private Specification<TeacherMO> getTeachingHoursSpecifications(TeacherQueryParams teacherQueryParams) {
    return (root, query, cb) -> {
      Join<TeacherMO, TeachingHourMO> join = root.join(TeacherMO_.teachingHours, JoinType.LEFT);
      return cb.and(
              teacherQueryParams.getDayOfWeek() != null ? cb.equal(join.get(TeachingHourMO_.dayOfWeek), teacherQueryParams.getDayOfWeek()) : cb.conjunction(),
              teacherQueryParams.getInstantNow() != null ? cb.lessThanOrEqualTo(join.get(TeachingHourMO_.startHour), teacherQueryParams.getInstantNow()) : cb.conjunction(),
              teacherQueryParams.getInstantNow() != null ? cb.greaterThanOrEqualTo(join.get(TeachingHourMO_.endHour), teacherQueryParams.getInstantNow()) : cb.conjunction(),
              teacherQueryParams.getOccupation() != null ? cb.equal(join.get(TeachingHourMO_.occupation), teacherQueryParams.getOccupation()) : cb.conjunction()
      );
    };
  }

  private Specification<TeacherMO> getDayOfWeek(String dayOfWeek) {
    return (root, query, cb) ->
        cb.equal(root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.dayOfWeek), dayOfWeek);
  }

  private Specification<TeacherMO> getStartHour(LocalTime startHour) {
    return (root, query, cb) ->
        cb.lessThanOrEqualTo(
            root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.startHour), startHour);
  }

  private Specification<TeacherMO> getEndHour(LocalTime endHour) {
    return (root, query, cb) ->
        cb.greaterThanOrEqualTo(
            root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.endHour), endHour);
  }

  private Specification<TeacherMO> getOccupation(String occupation) {
    return (root, query, cb) ->
        cb.equal(root.join(TeacherMO_.teachingHours).get(TeachingHourMO_.occupation), occupation);
  }
}
