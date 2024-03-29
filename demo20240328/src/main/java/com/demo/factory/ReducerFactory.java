package com.demo.factory;

import com.demo.detector.Detector;
import com.demo.strategy.entity.IdenticalResult;
import com.demo.strategy.enums.ReducerType;
import com.demo.strategy.service.Reducer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 缩减器工厂
 *
 * @author joe wong
 */
@Component
public class ReducerFactory implements ApplicationContextAware {

    @Autowired
    private Detector identicalDetector;

    private static final Map<ReducerType, Reducer> REDUCER_MAP = new HashMap<>();

    private static final Map<Boolean, ReducerType> REDUCER_TYPE_MAP = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        applicationContext.getBeansOfType(Reducer.class)
                .values().forEach(x -> REDUCER_MAP.put(x.getReducerType(), x));

        new ArrayList<>(Arrays.asList(ReducerType.values())).forEach(x -> REDUCER_TYPE_MAP.put(x.getIfReplaceCharacter(), x));
    }

    /**
     * 获取缩减器
     *
     * @param replaceCharacter 是否替换字符
     * @return 缩减器
     */
    public Reducer getReducer(boolean replaceCharacter) {
        return REDUCER_MAP.get(REDUCER_TYPE_MAP.get(replaceCharacter));
    }

    /**
     * 递归缩减
     *
     * @param source 源字符串
     * @return 递归缩减后的目标字符串
     */
    public String recursiveReduce(String source, boolean replaceCharacter) {
        Reducer reducer = getReducer(replaceCharacter);
        IdenticalResult ir = identicalDetector.detectorIdentical(source);
        String after = reducer.reduce(source, ir);
        if (identicalDetector.detectorIdentical(after).getIfIdentical()) {
            after = recursiveReduce(after, replaceCharacter);
        }
        return after;
    }
}
