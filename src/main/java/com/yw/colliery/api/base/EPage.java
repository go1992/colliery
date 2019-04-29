package com.yw.colliery.api.base;

public enum EPage {
	
	PageNum("pageNum",1),
	PageSize("pageSize",10);
	
	public final String key;
	public final int value;
	
	private EPage(String key, int value) {
		this.key = key;
		this.value = value;
	}

	

}
