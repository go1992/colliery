package com.example.demo.absys;

public enum ESessionKey {
	
	User("accountUser")
	,DeptsIds("userDeptsIds")
	,DeptsIdStr("userDeptsIdsStr")
	,
	;
	
	public final String key;
	
	private ESessionKey(String key) {
		this.key = key;
	}

	

}
