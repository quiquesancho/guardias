package com.edu.quique.guardias.Services;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.edu.quique.guardias.Models.Docente;

public interface DocumentoService {

    public void subirXML(InputStream archivo) throws ParserConfigurationException, SAXException, IOException, ParseException;
    
}