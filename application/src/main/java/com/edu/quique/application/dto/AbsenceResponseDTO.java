package com.edu.quique.application.dto;

import com.edu.quique.application.domain.Absence;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AbsenceResponseDTO {
    private List<Absence> absences;
}
