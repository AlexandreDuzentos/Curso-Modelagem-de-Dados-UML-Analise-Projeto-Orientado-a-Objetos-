package com.alexandre.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.cursomc.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
