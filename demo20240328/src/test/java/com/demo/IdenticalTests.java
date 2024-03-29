package com.demo;

import com.demo.factory.ReducerFactory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdenticalTests {

    @Autowired
    ReducerFactory reducerFactory;

    @Test
    void stage1Test() {
        Assert.assertEquals("aabc", reducerFactory.recursiveReduce("aabc", false));
        Assert.assertEquals("aabcc", reducerFactory.recursiveReduce("aabcc", false));
        Assert.assertEquals("aab", reducerFactory.recursiveReduce("aabccc", false));
        Assert.assertEquals("aab", reducerFactory.recursiveReduce("aabccccc", false));
        Assert.assertEquals("d", reducerFactory.recursiveReduce("aabcccbbad", false));
        Assert.assertEquals("abbcd", reducerFactory.recursiveReduce("abcccbcd", false));
        Assert.assertEquals("de", reducerFactory.recursiveReduce("aabccccbbadccce", false));
        Assert.assertEquals("ade", reducerFactory.recursiveReduce("aaaabccccbbadccceffffff", false));
    }

    @Test
    void stage2Test() {
        Assert.assertEquals("aabc", reducerFactory.recursiveReduce("aabc", true));
        Assert.assertEquals("aabcc", reducerFactory.recursiveReduce("aabcc", true));
        Assert.assertEquals("aabb", reducerFactory.recursiveReduce("aabccc", true));
        Assert.assertEquals("aabb", reducerFactory.recursiveReduce("aabccccc", true));
        Assert.assertEquals("d", reducerFactory.recursiveReduce("aabcccbbad", true));
        Assert.assertEquals("aacd", reducerFactory.recursiveReduce("abcccbcd", true));
        Assert.assertEquals("dbe", reducerFactory.recursiveReduce("aabccccbbadccce", true));
        Assert.assertEquals("aadbee", reducerFactory.recursiveReduce("aaaabccccbbadccceffffff", true));
    }

}
