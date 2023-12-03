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
public class Registry implements Comparable<Registry> {
    private Long registryId;
    private Absence absence;
    private TimetableGroup timetableGroup;
    private Teacher teacherGuard;
    private String observation;
    private OffsetDateTime assignedTime;
    private Boolean isAssigned;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o instanceof Registry registry)) {
            return this.registryId.equals(registry.getRegistryId());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.registryId.hashCode();
    }

    @Override
    public int compareTo(Registry o) {
        return this.registryId.compareTo(o.getRegistryId());
    }
}
