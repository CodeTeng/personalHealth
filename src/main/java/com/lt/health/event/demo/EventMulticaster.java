package com.lt.health.event.demo;

/**
 * @description: 事件广播器接口
 * 1. 负责事件监听器的管理 (注册监听器&移除监听器，将事件和监听器关联起来)
 * 2. 负责事件的广播 (将事件广播给所有的监听器，对该事件感兴趣的监听器会处理该事件)
 * @author: 狂小腾
 * @date: 2022/4/2 16:18
 */
public interface EventMulticaster {

    /**
     * 将事件广播给所有的监听器
     *
     * @param event 事件
     */
    void multicastEvent(AbstractEvent event);

    /**
     * 添加一个事件监听器(监听器中包含了监听器能够处理的事件)
     *
     * @param listener 具体的事件监听器
     */
    void addEventListener(EventListener<?> listener);

    /**
     * 移除事件监听器
     *
     * @param listener 需要移除的具体的事件监听器
     */
    void removeEventListener(EventListener<?> listener);
}
