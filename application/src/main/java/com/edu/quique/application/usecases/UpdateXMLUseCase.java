package com.edu.quique.application.usecases;

import com.edu.quique.application.ports.in.services.DocumentServicePort;
import com.edu.quique.application.ports.in.usecases.UpdateXMLUseCasePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

@Service
@AllArgsConstructor
public class UpdateXMLUseCase implements UpdateXMLUseCasePort {
    private DocumentServicePort documentService;
    @Override
    public void execute(InputStream file) throws ParserConfigurationException, IOException, ParseException, SAXException {
        documentService.updateXML(file);
    }
}
