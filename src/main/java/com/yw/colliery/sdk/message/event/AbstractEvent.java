package com.yw.colliery.sdk.message.event;

/**
 * @Author renzhiqiang
 * @Description 抽象事件类型
 * @Date 2019-04-10
 **/
public abstract class AbstractEvent<T> implements Event<T>{
    /**事件数据体.**/
    protected T data;
    /**事件名称.**/
    protected String name;

    protected AbstractEvent(T data, String name){
        this.name = name;
        this.data = data;
    }


}
