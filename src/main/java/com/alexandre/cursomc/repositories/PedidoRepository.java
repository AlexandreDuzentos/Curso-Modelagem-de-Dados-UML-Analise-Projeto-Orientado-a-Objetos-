package com.alexandre.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alexandre.cursomc.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
