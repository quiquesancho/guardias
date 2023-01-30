package com.edu.quique.guardias.Controllers;

import com.edu.quique.guardias.Entity.AbstractEntity;
import com.edu.quique.guardias.ServiceImpl.DocumentServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/documento")
public class DocumentController {
	
	private final String ERROR = "No se han recibido archivo o no es el formato correcto";

    @Autowired
    DocumentServiceImpl docService;
    
    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @PostMapping
    public ResponseEntity<?> subirArchivo(MultipartFile archivo){
    	
    	
    		if(!archivo.isEmpty() && archivo.getSize() > AbstractEntity.FALSO_LONG){
    			try {
    				docService.subirXML(archivo.getInputStream());
    				return ResponseEntity.ok().build();
    			} catch (Exception e) {
    				logger.debug(e.getMessage());
    				return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
    			}	
            } 
    		
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ERROR);
     
    }    
}