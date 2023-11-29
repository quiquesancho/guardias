package com.edu.quique.application.ports.in.services;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public interface DocumentServicePort {

  void updateXML(InputStream file)
      throws ParserConfigurationException, SAXException, IOException, ParseException;
}