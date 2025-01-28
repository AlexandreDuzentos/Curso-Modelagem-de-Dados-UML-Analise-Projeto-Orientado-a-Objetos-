package com.alexandre.cursomc.domain.enums;

/* 
 * Essa implementação foi feita para termos o controle do código
 * atribuído a cada um dos valores da enumeração.
 * */
public enum TipoCliente {
  PESSOA_FISICA(1, "Pessoa física"),
  PESSOA_JURIDICA(2, "Pessoa jurídica");
  
  private int codigo;
  private String descricao;
  
  private TipoCliente(int codigo, String descricao) {
	  this.codigo = codigo;
	  this.descricao = descricao;
  }
  
  public int getCodigo() {
	  return this.codigo;
  }
  
  public String getDescricao() {
	  return this.descricao;
  }
  
  public static TipoCliente toEnum(Integer codigo) {
	  if(codigo == null) {
		  return null;
	  } 
	  
	  for(TipoCliente tc: TipoCliente.values()) {
		   if(codigo == tc.getCodigo()) {
			   return tc;
		   }
	  }
	  
	  throw new IllegalArgumentException("Id inválido"+ codigo);
  }
}
