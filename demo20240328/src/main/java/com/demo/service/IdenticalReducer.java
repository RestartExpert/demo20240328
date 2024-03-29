package com.demo.service;

import com.demo.entity.IdenticalResult;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * 相同字符缩减器
 *
 * @author joe wong
 */
@Component
public class IdenticalReducer {

    /**
     * 递归缩减
     *
     * @param source 源字符串
     * @return 递归缩减后的目标字符串
     */
    public String recursiveReduce(String source, boolean replaceCharacter) {
        IdenticalResult ir = detectorIdentical(source);
        String after = reduceIdentical(source, ir, replaceCharacter);
        if (detectorIdentical(after).getIfIdentical()) {
            after = recursiveReduce(after, replaceCharacter);
        }
        return after;
    }

    /**
     * 判断相同字符
     *
     * @param source 源字符串
     * @return 相同字符结果
     */
    private IdenticalResult detectorIdentical(String source) {
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

    /**
     * 缩减相同字符
     *
     * @param source 源字符串
     * @param ir     相同字符结果
     * @return 单次缩减后的字符串
     */
    private String reduceIdentical(String source, IdenticalResult ir) {
        source = Optional.ofNullable(source).orElse("");
        ir = Optional.ofNullable(ir).orElse(new IdenticalResult());

        char[] srcChars = source.toCharArray();
        Set<Character> charSet = ir.getCharSet();
        int index = 0;
        char[] resChars = new char[srcChars.length];

        int count = 0;
        for (char srcChar : srcChars) {
            if (charSet.contains(srcChar) && count < ir.getCount()) {
                // 排除相同字符集里面包含的字符
                resChars[index] = ' ';
                count++;
            } else {
                resChars[index] = srcChar;
            }
            index++;
        }
        // 去掉因初始化char数组而出现的空格
        return new String(resChars).trim().replace(" ", "");
    }

    /**
     * 缩减相同字符
     *
     * @param source           源字符串
     * @param ir               相同字符结果
     * @param replaceCharacter 是否需要替换字符
     * @return 单次缩减并替换后的字符串
     */
    private String reduceIdentical(String source, IdenticalResult ir, boolean replaceCharacter) {
        if (!replaceCharacter) {
            return reduceIdentical(source, ir);
        }
        source = Optional.ofNullable(source).orElse("");
        ir = Optional.ofNullable(ir).orElse(new IdenticalResult());

        char[] srcChars = source.toCharArray();
        Set<Character> charSet = ir.getCharSet();
        int index = 0;
        char[] resChars = new char[srcChars.length];

        int count = 0;
        for (char srcChar : srcChars) {
            if (charSet.contains(srcChar) && count < ir.getCount()) {
                // 替换相同字符集里面包含的字符
                if (count == 0) {
                    if (srcChar == 'a') {
                        resChars[index] = ' ';
                    } else {
                        resChars[index] = (char) (srcChar - 1);
                    }
                } else {
                    resChars[index] = ' ';
                }
                count++;
            } else {
                resChars[index] = srcChar;
            }
            index++;
        }
        // 去掉因初始化char数组而出现的空格
        return new String(resChars).trim().replace(" ", "");
    }

}
