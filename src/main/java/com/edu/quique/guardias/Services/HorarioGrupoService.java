package com.edu.quique.guardias.Services;

import java.util.List;
import java.util.Optional;

import com.edu.quique.guardias.Models.Horariogrupo;

public interface HorarioGrupoService {
	
	public List<Horariogrupo> findAll();
	
	public Optional<Horariogrupo> findById(Long id);
	
	public Horariogrupo save(Horariogrupo hg);
	
	public void deteleAll();

}
