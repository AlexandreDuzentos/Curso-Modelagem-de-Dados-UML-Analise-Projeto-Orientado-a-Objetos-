package com.alexandre.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.cursomc.domain.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
