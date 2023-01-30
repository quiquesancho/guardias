package com.edu.quique.guardias.Services;

import java.util.List;
import java.util.Optional;

import com.edu.quique.guardias.Models.Docente;

public interface DocenteService {

    public List<Docente> findAll();

    public Optional<Docente> findById(String dni);

    public Docente save(Docente d);
    
    public void deleteAll();
}