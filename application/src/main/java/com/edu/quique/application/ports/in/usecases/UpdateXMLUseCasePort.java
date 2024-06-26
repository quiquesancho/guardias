package com.edu.quique.application.ports.in.usecases;

import java.io.InputStream;

public interface UpdateXMLUseCasePort {
  void execute(InputStream file);
}
