package com.demo.strategy.service.impl;

import com.demo.strategy.entity.IdenticalResult;
import com.demo.strategy.enums.ReducerType;
import com.demo.strategy.service.Reducer;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

/**
 * 替换字符缩减器
 *
 * @author joe wong
 */
@Component
public class ReplaceCharacterReducer implements Reducer {

    @Override
    public String reduce(String source, IdenticalResult ir) {
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

    @Override
    public ReducerType getReducerType() {
        return ReducerType.REPLACE_CHARACTER;
    }

}
