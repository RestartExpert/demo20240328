package com.demo;

import com.demo.service.IdenticalReducer;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdenticalTests {

    @Autowired
    IdenticalReducer identicalReducer;

    @Test
    void stage1Test() {
        Assert.assertEquals("aabc", identicalReducer.recursiveReduce("aabc", false));
        Assert.assertEquals("aabcc", identicalReducer.recursiveReduce("aabcc", false));
        Assert.assertEquals("aab", identicalReducer.recursiveReduce("aabccc", false));
        Assert.assertEquals("aab", identicalReducer.recursiveReduce("aabccccc", false));
        Assert.assertEquals("d", identicalReducer.recursiveReduce("aabcccbbad", false));
        Assert.assertEquals("abbcd", identicalReducer.recursiveReduce("abcccbcd", false));
        Assert.assertEquals("de", identicalReducer.recursiveReduce("aabccccbbadccce", false));
        Assert.assertEquals("ade", identicalReducer.recursiveReduce("aaaabccccbbadccceffffff", false));
    }

    @Test
    void stage2Test() {
        Assert.assertEquals("aabc", identicalReducer.recursiveReduce("aabc", true));
        Assert.assertEquals("aabcc", identicalReducer.recursiveReduce("aabcc", true));
        Assert.assertEquals("aabb", identicalReducer.recursiveReduce("aabccc", true));
        Assert.assertEquals("aabb", identicalReducer.recursiveReduce("aabccccc", true));
        Assert.assertEquals("d", identicalReducer.recursiveReduce("aabcccbbad", true));
        Assert.assertEquals("aacd", identicalReducer.recursiveReduce("abcccbcd", true));
        Assert.assertEquals("dbe", identicalReducer.recursiveReduce("aabccccbbadccce", true));
        Assert.assertEquals("aadbee", identicalReducer.recursiveReduce("aaaabccccbbadccceffffff", true));
    }

}
