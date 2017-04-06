package com.lille1.tps.car.config.type;

public enum TYPE_FILE {
	ASCII("A"), 
	EBCDIC("E"), 
	IMAGE("I"), 
	LOCAL_FORMAT("L");
	
	private String code;
	
	private TYPE_FILE(final String code) {
		this.code = code;
	}
	
	public static TYPE_FILE valueOfCode(String code) {
		for(TYPE_FILE type : TYPE_FILE.values()) {
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
