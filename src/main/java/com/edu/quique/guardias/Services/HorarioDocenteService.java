package com.edu.quique.guardias.Services;

import java.util.List;
import java.util.Optional;

import com.edu.quique.guardias.Models.Horariodocente;

public interface HorarioDocenteService {
	
	public List<Horariodocente> findAll();
	
	public Optional<Horariodocente> findById(Long id);
	
	public Horariodocente save(Horariodocente hd);
	
	public void deleteAll();

}
