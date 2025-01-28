package com.alexandre.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alexandre.cursomc.domain.Pedido;
import com.alexandre.cursomc.repositories.PedidoRepository;
import com.alexandre.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
  
	public Pedido buscar(Integer id) {
		Optional<Pedido> cat = repo.findById(id);
		return cat.orElseThrow(() -> new ObjectNotFoundException("Objecto"
				+ "não encontrado! "+ id +", Tipo: "+ Pedido.class.getName()));
		
	}
}
