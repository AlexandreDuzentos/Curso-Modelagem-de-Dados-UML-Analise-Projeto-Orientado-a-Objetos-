package com.alexandre.cursomc.domain;

import java.io.Serializable;
import java.util.Objects;

import com.alexandre.cursomc.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
abstract public class Pagamento implements Serializable {
    
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private int estado;
	
	
	/* A annotation MapsId indica que os IDs da entidade pagamento
	 * serão compartilhados com a entidade pedido, ou seja, o id
	 * gerado pelo pedido será que o mesmo que o id do pagamento.
	 * 
	 * O id gerado para o pedido será o mesmo do pagamento associado
	 * a ele.
	 * */
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId 
	private Pedido pedido;
	
	public Pagamento() {}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagamento other = (Pagamento) obj;
		return Objects.equals(id, other.id);
	}
	
		
}
