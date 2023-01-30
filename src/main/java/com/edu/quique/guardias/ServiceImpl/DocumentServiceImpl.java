package com.edu.quique.guardias.ServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.edu.quique.guardias.Entity.AbstractEntity;
import com.edu.quique.guardias.Models.Docente;
import com.edu.quique.guardias.Models.Horariodocente;
import com.edu.quique.guardias.Models.Horariogrupo;
import com.edu.quique.guardias.Services.DocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

@Service
public class DocumentServiceImpl implements DocumentoService{
	
	//Strings para los parámetros de las etiquetas XML para Docente
	private final String DOCENTE = "docente";
	private final String HORARIO_OCUPACION = "horario_ocupacion";
	private final String HORARIO_GRUPO = "horario_grupo";
	private final String DOCUMENTO = "documento";
	private final String CORREO = "correo";
	private final String NOMBRE = "nombre";
	private final String APELLIDO1 = "apellido1";
	private final String APELLIDO2 = "apellido2";
	//Strings para los parámetros de las etiquetas XML para Horario_ocupacion
	private final String DIA_SEMANA = "dia_semana";
	private final String DESDE = "hora_desde";
	private final String HASTA = "hora_hasta";
	private final String OCUPACION = "ocupacion";
	private final String AULA = "aula";
	private final String CONTENIDO = "contenido";
	private final String GRUPO = "grupo";
	
	@Autowired
	private DocenteServiceImpl doService;
	@Autowired
	private HorarioDocenteServiceImpl hdService;
	@Autowired
	private HorarioGrupoServiceImpl hgService;
	
	//Formato para obtener la hora e introducirla en la clase Date
	private SimpleDateFormat format = new SimpleDateFormat("HH:mm");

    @Override
    public void subirXML(InputStream archivo) throws ParserConfigurationException, SAXException, IOException, ParseException {
        
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(archivo);
        List<Docente> docentes = new ArrayList<Docente>();
        NodeList nodeList = doc.getDocumentElement().getChildNodes();
        
        hdService.deleteAll();
        hgService.deteleAll();
        doService.deleteAll();
        
        for(int i = 0 ; i < nodeList.getLength() ; i++) {
        	Node node = nodeList.item(i);
        	if(node.getNodeType() == Node.ELEMENT_NODE) {
        		Element elem = (Element) node;
        		NodeList nodeListDocentes = elem.getChildNodes();
        		for(int j = 0 ; j < nodeListDocentes.getLength() ; j++) {
        			Node node2 = nodeListDocentes.item(j);
        			if(node2.getNodeType() == Node.ELEMENT_NODE) {
        				Element elem2 = (Element) node2;
        				if(elem2.getTagName() == DOCENTE) {
        					Docente d = new Docente();
        					
        					d.setDni(elem2.getAttribute(DOCUMENTO));
        					d.setEmail(elem2.getAttribute(CORREO));
        					d.setNombre(elem2.getAttribute(NOMBRE));
        					d.setPrimapel(elem2.getAttribute(APELLIDO1));
        					d.setSegapel(elem2.getAttribute(APELLIDO2));
        					
        					doService.save(d);
        					
        				} else if (elem2.getTagName() == HORARIO_OCUPACION) {
        					Optional<Docente> d1 = doService.findById(elem2.getAttribute(DOCUMENTO));
        					
        					Horariodocente hd = new Horariodocente();
        					
							hd.setDesde(format.parse(elem2.getAttribute(DESDE)));
        					hd.setDiaSemana(elem2.getAttribute(DIA_SEMANA));
        					hd.setDocente(d1.isPresent() ? d1.get() : null);
        					hd.setHasta(format.parse(elem2.getAttribute(HASTA)));
        					hd.setOcupacion(elem2.getAttribute(OCUPACION));
        					
        					hdService.save(hd);
        					
        				} else if (elem2.getTagName() == HORARIO_GRUPO) {
        					Optional<Docente> d1 = doService.findById(elem2.getAttribute(DOCENTE));
        					
        					Horariogrupo hg = new Horariogrupo();
        					
        					hg.setAula(elem2.getAttribute(AULA));
        					hg.setContenido(elem2.getAttribute(CONTENIDO));
        					hg.setDesde(format.parse(elem2.getAttribute(DESDE)));
        					hg.setDiaSemana(elem2.getAttribute(DIA_SEMANA));
        					hg.setDocente(d1.isPresent() ? d1.get() : null);
        					hg.setGrupo(elem2.getAttribute(GRUPO));
        					hg.setHasta(format.parse(elem2.getAttribute(HASTA)));
        					
        					hgService.save(hg);
        					
        				}
        			}        			
        		}       		
        	}
        }   
    }    
}