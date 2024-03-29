package com.demo.detector.impl;

import com.demo.detector.Detector;
import com.demo.strategy.entity.IdenticalResult;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 重复字符串检测器
 *
 * @author joe wong
 */
@Component
public class IdenticalDetector implements Detector {

    /**
     * 判断相同字符
     *
     * @param source 源字符串
     * @return 相同字符结果
     */
    @Override
    public IdenticalResult detectorIdentical(String source) {
        source = Optional.ofNullable(source).orElse("");

        IdenticalResult result = new IdenticalResult();
        char[] chars = source.toCharArray();

        // 相同字符数阈值
        int consecutiveCount = 3;
        if (chars.length < consecutiveCount) {
            return result;
        }

        boolean continuityEnd = false;
        for (int i = 0; i < chars.length; i++) {
            if (continuityEnd) {
                break;
            }
            // 出现连续3个及以上相同的字符，设置结果
            boolean ifIdentical = (i + 2 < chars.length) && (chars[i] == chars[i + 1]) && (chars[i] == chars[i + 2]);

            if (ifIdentical) {
                result.setIfIdentical(true);
                result.addChar(chars[i]);

                // 计算连续相同字符出现次数
                int range = 0;
                while (true) {
                    if ((i + range) < chars.length && chars[i + range] == chars[i]) {
                        range++;
                    } else {
                        i = i + range;
                        result.setCount(range);
                        continuityEnd = true;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
