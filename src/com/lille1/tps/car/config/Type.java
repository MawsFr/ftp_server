package com.lille1.tps.car.config;

public enum Type {
	ASCII("A"), 
	EBCDIC("E"), 
	IMAGE("I"), 
	LOCAL_FORMAT("L");
	
	private String code;
	
	private Type(final String code) {
		this.code = code;
	}
	
	public static Type valueOfCode(String code) {
		for(Type type : Type.values()) {
			if(type.code.equals(code)) {
				return type;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() {
		return code;
	}

}
