package com.alexandre.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.cursomc.domain.Cliente;
import com.alexandre.cursomc.repositories.ClienteRepository;
import com.alexandre.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
  
	public Cliente buscar(Integer id) {
		Optional<Cliente> cat = repo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto"
				+ "não encontrado! "+ id +", Tipo: "+ Cliente.class.getName()));
		
	}
}
