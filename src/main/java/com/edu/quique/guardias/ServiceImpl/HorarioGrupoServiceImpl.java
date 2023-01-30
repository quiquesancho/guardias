package com.edu.quique.guardias.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.edu.quique.guardias.Models.Horariogrupo;
import com.edu.quique.guardias.Repository.HorarioGrupoRepository;
import com.edu.quique.guardias.Services.HorarioGrupoService;

@Service
public class HorarioGrupoServiceImpl implements HorarioGrupoService{
	
	@Autowired
	HorarioGrupoRepository hgRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Horariogrupo> findAll() {
		return (List<Horariogrupo>)hgRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Horariogrupo> findById(Long id) {
		return hgRepository.findById(id);
	}

	@Override
	@Transactional
	public Horariogrupo save(Horariogrupo hg) {
		return hgRepository.save(hg);
	}

	@Override
	@Transactional
	public void deteleAll() {
		hgRepository.deleteAll();		
	}

}
