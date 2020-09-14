package com.felipebueno.springshop.domain.enums;

public enum StatePayment {
	
	PENDENTE(1, "pendente"),
	QUITADO(2, "quitado"),
	CANCELADO(3, "Cancelado");
	
	private int code;
	private String description;
	
	private StatePayment(int code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public static StatePayment toEnum(Integer code) {
		if (code == null) {
			return null;
		}
		
		for(StatePayment type : StatePayment.values()) {
			if (code.equals(type.getCode())) {
				return type;
			}
		}
		
		throw new IllegalArgumentException("Invalid ID: " + code);
	}
	
}
