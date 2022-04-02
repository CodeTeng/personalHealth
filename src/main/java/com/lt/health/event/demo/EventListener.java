package com.lt.health.event.demo;

/**
 * @param <E> 当前监听器感兴趣的事件
 * @description: 事件监听器的接口
 * @author: 狂小腾
 * @date: 2022/4/2 16:15
 */
public interface EventListener<E extends AbstractEvent> {

    /**
     * 处理事件
     *
     * @param event 事件
     */
    void onEvent(E event);
}
