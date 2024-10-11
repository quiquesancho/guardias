package com.edu.quique.application.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistryAbsence implements Comparable<RegistryAbsence> {
  private Long registryAbsenceId;
  private Absence absence;
  private Teacher teacherGuard;
  private String observation;
  private OffsetDateTime checkGuard;

  public boolean isChecked() {
    return this.checkGuard != null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if ((o instanceof RegistryAbsence registryAbsence)) {
      return this.registryAbsenceId.equals(registryAbsence.getRegistryAbsenceId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return this.registryAbsenceId.hashCode();
  }

  @Override
  public int compareTo(RegistryAbsence o) {
    return this.registryAbsenceId.compareTo(o.getRegistryAbsenceId());
  }
}