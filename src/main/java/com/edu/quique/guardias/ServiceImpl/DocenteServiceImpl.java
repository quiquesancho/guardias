package com.edu.quique.guardias.ServiceImpl;

import java.util.List;
import java.util.Optional;

import com.edu.quique.guardias.Models.Docente;
import com.edu.quique.guardias.Repository.DocenteRepository;
import com.edu.quique.guardias.Services.DocenteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DocenteServiceImpl implements DocenteService{

    @Autowired
    DocenteRepository doRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Docente> findById(String dni) {
        return doRepository.findById(dni);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Docente> findAll() {
        return (List<Docente>) doRepository.findAll();
    }

    @Override
    @Transactional
    public Docente save(Docente d) { 
        return doRepository.save(d);
    }

	@Override
	@Transactional
	public void deleteAll() {
		doRepository.deleteAll();
		
	}
    
}