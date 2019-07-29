package com.yw.colliery.api.base;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EPage {
	
	PageNum("pageNum",1),
	PageSize("pageSize",10)
	;
	public final String key;
	public final int value;

}
