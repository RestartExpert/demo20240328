package com.demo.detector;

import com.demo.strategy.entity.IdenticalResult;

/**
 * 重复字符串检测器
 *
 * @author joe wong
 */
public interface Detector {

    IdenticalResult detectorIdentical(String source);
}
