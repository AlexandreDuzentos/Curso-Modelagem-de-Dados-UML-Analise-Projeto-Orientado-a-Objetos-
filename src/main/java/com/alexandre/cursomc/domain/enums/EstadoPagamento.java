package com.alexandre.cursomc.domain.enums;

public enum EstadoPagamento {
    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");
	
	private Integer cod;
	private String desc;
	
	private EstadoPagamento(Integer cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public Integer getCod() {
		return this.cod;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public static EstadoPagamento toEnum(Integer cod) {
		if(cod == null) {
			return null;
		}
		
		for(EstadoPagamento est: EstadoPagamento.values()) {
			  if(cod == est.getCod()) {
				  return est;
			  }
		}
		
		throw new IllegalArgumentException("Id inválido"+ cod);
	}
}
