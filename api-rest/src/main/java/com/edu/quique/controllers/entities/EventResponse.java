package com.edu.quique.controllers.entities;

import com.edu.quique.application.domain.RegistryAbsence;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EventResponse {
    private RegistryAbsence registryAbsence;
}
