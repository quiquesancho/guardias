package com.edu.quique.application.service;

import com.edu.quique.application.ports.in.services.AbsenceServicePort;
import com.edu.quique.application.ports.in.services.RegistryServicePort;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
@Transactional
public class RegistryService implements RegistryServicePort {
  private AbsenceServicePort absenceService;
  @Override
  @Scheduled(cron = "10 8-19/15 * * MON-FRI")
  public void createRegistriesBeforeBreak() {}
}
