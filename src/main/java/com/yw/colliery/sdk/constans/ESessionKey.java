package com.yw.colliery.sdk.constans;


public enum ESessionKey {
	
	User("accountUser")
	,DeptsIds("userDeptsIds")
	,DeptsIdStr("userDeptsIdsStr")
	,
	;
	
	public final String key;
	
	ESessionKey(String key) {
		this.key = key;
	}

	

}
