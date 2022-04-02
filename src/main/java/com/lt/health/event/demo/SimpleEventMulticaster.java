package com.lt.health.event.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 具体的事件广播器
 * @author: 狂小腾
 * @date: 2022/4/2 16:26
 */
public class SimpleEventMulticaster implements EventMulticaster {

    private Map<Class<?>, List<EventListener>> eventObjectEventListenerMap
            = new ConcurrentHashMap<>();

    @Override
    public void multicastEvent(AbstractEvent event) {
        // 根据事件获取监听器
        List<EventListener> eventListeners = eventObjectEventListenerMap.get(event.getClass());
        if (eventListeners != null) {
            for (EventListener eventListener : eventListeners) {
                // 监听器处理事件
                eventListener.onEvent(event);
            }
        }
    }

    @Override
    public void addEventListener(EventListener<?> listener) {
        // 获取事件监听器需要监听的事件类型
        Class<?> eventType = this.getEventType(listener);
        // 根据事件类型获取监听器
        List<EventListener> eventListeners = eventObjectEventListenerMap.get(eventType);
        if (eventListeners == null) {
            // 监听器为空
            eventListeners = new ArrayList<>();
            eventObjectEventListenerMap.put(eventType, eventListeners);
        }
        // 不为空 直接add
        eventListeners.add(listener);
    }

    @Override
    public void removeEventListener(EventListener<?> listener) {
        // 获取事件监听器需要监听的事件类型
        Class<?> eventType = this.getEventType(listener);
        // 根据事件类型获取监听器
        List<EventListener> eventListeners = eventObjectEventListenerMap.get(eventType);
        if (eventListeners != null) {
            eventListeners.remove(listener);
        }
    }

    /**
     * 根据事件监听器获取监听事件的类型
     *
     * @param listener 事件监听器
     * @return 监听事件的类型
     */
    protected Class<?> getEventType(EventListener listener) {
        // 获取抽象接口的类型
        ParameterizedType parameterizedType = (ParameterizedType) listener.getClass().getGenericInterfaces()[0];
        // 获取该接口方法的参数类型
        Type eventType = parameterizedType.getActualTypeArguments()[0];
        return (Class<?>) eventType;
    }
}
