package com.edu.quique.application.usecases;

import com.edu.quique.application.ports.in.services.DocumentServicePort;
import com.edu.quique.application.ports.in.usecases.UpdateXMLUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@AllArgsConstructor
public class UpdateXMLUseCase implements UpdateXMLUseCasePort {
  private DocumentServicePort documentService;

  @Override
  public void execute(InputStream file) {
    documentService.updateXML(file);
  }
}
