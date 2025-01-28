package com.alexandre.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.alexandre.cursomc.domain.enums.TipoCliente;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {
	
   private static final long serialVersionUID = 1L;
	
   @Id
   @GeneratedValue(strategy=GenerationType.IDENTITY)
   private Integer id;
   private String nome;
   private String email;
   private String cpfOuCnpj;
   
   private int tipo;
   
   /* Dizendo que endereços devem ser serializados pelo jackson */
   @JsonManagedReference
   @OneToMany(mappedBy="cliente")
   private List<Endereco> enderecos = new ArrayList<>();
   
   /* Por conta da entidade telefone ser muito simples, na implementação
    * optou-se por não cria-la, ao invés disso será usada uma coleção
    * do tipo Set(que não admite repetições) para guardar os telefones
    * de um cliente
    * */
   @ElementCollection
   @CollectionTable(name="telefone")
    private Set<String> telefones = new HashSet<>();
   
   @JsonBackReference
   @OneToMany(mappedBy="cliente")
   List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj,
			      TipoCliente tipoCliente) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		tipo = tipoCliente.getCodigo();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipo = tipoCliente.getCodigo();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
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
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
	
	
   
   
   
   
   
}
