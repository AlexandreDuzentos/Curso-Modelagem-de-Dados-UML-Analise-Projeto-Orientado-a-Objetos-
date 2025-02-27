package com.alexandre.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.cursomc.domain.Categoria;
import com.alexandre.cursomc.repositories.CategoriaRepository;
import com.alexandre.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
  
	public Categoria buscar(Integer id) {
		Optional<Categoria> cat = repo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto"
				+ "não encontrado! "+ id +", Tipo: "+ Categoria.class.getName()));
		
	}
}
