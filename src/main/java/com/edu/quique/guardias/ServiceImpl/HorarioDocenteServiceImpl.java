package com.edu.quique.guardias.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.quique.guardias.Models.Horariodocente;
import com.edu.quique.guardias.Repository.HorarioDocenteRepository;
import com.edu.quique.guardias.Services.HorarioDocenteService;

@Service
public class HorarioDocenteServiceImpl implements HorarioDocenteService{
	
	@Autowired
	HorarioDocenteRepository hdRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Horariodocente> findAll() {
		return (List<Horariodocente>)hdRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Horariodocente> findById(Long id) {
		return hdRepository.findById(id);
	}

	@Override
	@Transactional
	public Horariodocente save(Horariodocente hd) {
		return hdRepository.save(hd);
	}

	@Override
	@Transactional
	public void deleteAll() {
		hdRepository.deleteAll();
		
	}
}
