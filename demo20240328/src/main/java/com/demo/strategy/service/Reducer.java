package com.demo.strategy.service;

import com.demo.strategy.entity.IdenticalResult;
import com.demo.strategy.enums.ReducerType;

/**
 * @author joe wong
 */
public interface Reducer {

    /**
     * 缩减相同字符
     *
     * @param source 源字符串
     * @param ir     相同字符结果
     * @return 单次缩减后的字符串
     */
    String reduce(String source, IdenticalResult ir);

    /**
     * 获取缩减器类型
     *
     * @return 缩减器类型
     */
    ReducerType getReducerType();

}
