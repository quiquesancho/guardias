package com.edu.quique.guardias.Controllers;

import java.util.List;
import java.util.Optional;

import com.edu.quique.guardias.Models.Docente;
import com.edu.quique.guardias.ServiceImpl.DocenteServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/docentes")
@CrossOrigin(origins = { "*" }, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
public class DocenteController {

    @Autowired
    DocenteServiceImpl doService;

    @GetMapping
    public ResponseEntity<?> listarDocentes(){
        List<Docente> listaDocentes = doService.findAll();
        return ResponseEntity.ok(listaDocentes);
    }

    @GetMapping("/{dni}")
    public ResponseEntity<?> getDocenteById(@PathVariable String dni){

       Optional<Docente> o = doService.findById(dni);

       return o.isPresent() ? ResponseEntity.ok(o.get()) : ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    
}