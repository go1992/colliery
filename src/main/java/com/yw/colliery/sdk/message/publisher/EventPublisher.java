package com.yw.colliery.sdk.message.publisher;

import com.yw.colliery.sdk.message.event.Event;
import com.yw.colliery.sdk.message.listener.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author renzhiqiang
 * @Description 事件发布器
 * @Date 2019-04-10
 **/
@Component
public class EventPublisher {
    /**监听器列表.**/
    private List<EventListener> listeners = new ArrayList<>();

    public void publish(Event event){
        for (EventListener listener : listeners){
            listener.listen(event);
        }
    }

    /**
     * 注册监听器
     * @param listener
     */
    public void registListener(EventListener listener){
        if (listeners.contains(listener)){
            return;
        }else {
            listeners.add(listener);
        }
    }

    /**
     * 反注册监听器
     * @param listener
     */
    public void unregister(EventListener listener){
        if (listeners.contains(listener)){
            listeners.remove(listener);
        }
    }
}
