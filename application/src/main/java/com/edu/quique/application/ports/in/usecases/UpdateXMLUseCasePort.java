package com.edu.quique.application.ports.in.usecases;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

public interface UpdateXMLUseCasePort {
    public void execute(InputStream file) throws ParserConfigurationException, IOException, ParseException, SAXException;
}
