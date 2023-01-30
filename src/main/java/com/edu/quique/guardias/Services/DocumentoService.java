package com.edu.quique.guardias.Services;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface DocumentoService {

    public void subirXML(InputStream archivo) throws ParserConfigurationException, SAXException, IOException, ParseException;
    
}