package com.lt.health.event.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 抽象事件对象---描述了发生什么事件的对象
 * @author: 狂小腾
 * @date: 2022/4/2 16:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEvent {

    /**
     * 事件源
     */
    private Object source;
}
