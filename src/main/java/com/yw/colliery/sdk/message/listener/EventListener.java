package com.yw.colliery.sdk.message.listener;


/**
 * @Author renzhiqiang
 * @Description  监听器
 * @Date 2019-04-10
 **/
public interface EventListener<T> {

    void listen(T event);
}
