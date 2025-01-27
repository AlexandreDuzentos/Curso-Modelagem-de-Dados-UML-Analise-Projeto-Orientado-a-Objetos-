package com.alexandre.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.cursomc.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
